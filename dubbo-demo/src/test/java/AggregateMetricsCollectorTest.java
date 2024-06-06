/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.MetricsConfig;
import org.apache.dubbo.config.nested.AggregationConfig;
import org.apache.dubbo.metrics.TestMetricsInvoker;
import org.apache.dubbo.metrics.collector.AggregateMetricsCollector;
import org.apache.dubbo.metrics.collector.DefaultMetricsCollector;
import org.apache.dubbo.metrics.event.MetricsDispatcher;
import org.apache.dubbo.metrics.event.RequestEvent;
import org.apache.dubbo.metrics.filter.MetricsFilter;
import org.apache.dubbo.metrics.model.MethodMetric;
import org.apache.dubbo.metrics.model.TimePair;
import org.apache.dubbo.metrics.model.key.MetricsKey;
import org.apache.dubbo.metrics.model.key.TypeWrapper;
import org.apache.dubbo.metrics.model.sample.GaugeMetricSample;
import org.apache.dubbo.metrics.model.sample.MetricSample;
import org.apache.dubbo.rpc.AppResponse;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.RpcInvocation;
import org.apache.dubbo.rpc.model.ApplicationModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.dubbo.common.constants.CommonConstants.GROUP_KEY;
import static org.apache.dubbo.common.constants.CommonConstants.VERSION_KEY;
import static org.apache.dubbo.common.constants.MetricsConstants.TAG_GROUP_KEY;
import static org.apache.dubbo.common.constants.MetricsConstants.TAG_INTERFACE_KEY;
import static org.apache.dubbo.common.constants.MetricsConstants.TAG_METHOD_KEY;
import static org.apache.dubbo.common.constants.MetricsConstants.TAG_VERSION_KEY;

class AggregateMetricsCollectorTest {

    private ApplicationModel applicationModel;
    private DefaultMetricsCollector defaultCollector;
    private String interfaceName;
    private String methodName;
    private String group;
    private String version;
    private RpcInvocation invocation;
    private String side;
    private MetricsDispatcher metricsDispatcher;
    private AggregateMetricsCollector collector;
    private MetricsFilter metricsFilter;

    @BeforeEach
    public void setup() {
        applicationModel = ApplicationModel.defaultModel();
        ApplicationConfig config = new ApplicationConfig();
        config.setName("MockMetrics");
        applicationModel.getApplicationConfigManager().setApplication(config);

        MetricsConfig metricsConfig = new MetricsConfig();
        AggregationConfig aggregationConfig = new AggregationConfig();
        aggregationConfig.setEnabled(true);
        aggregationConfig.setBucketNum(12);
        aggregationConfig.setTimeWindowSeconds(120);
        metricsConfig.setAggregation(aggregationConfig);
        applicationModel.getApplicationConfigManager().setMetrics(metricsConfig);
        metricsDispatcher = applicationModel.getBeanFactory().getOrRegisterBean(MetricsDispatcher.class);
        defaultCollector = applicationModel.getBeanFactory().getBean(DefaultMetricsCollector.class);
        collector = applicationModel.getBeanFactory().getOrRegisterBean(AggregateMetricsCollector.class);
        collector.setCollectEnabled(true);

        defaultCollector = new DefaultMetricsCollector(applicationModel);
        defaultCollector.setCollectEnabled(true);

        metricsFilter = new MetricsFilter();
        metricsFilter.setApplicationModel(applicationModel);

        interfaceName = "org.apache.dubbo.MockInterface";
        methodName = "mockMethod";
        group = "mockGroup";
        version = "1.0.0";
        invocation = new RpcInvocation(methodName, interfaceName, "serviceKey", null, null);
        invocation.setTargetServiceUniqueName(group + "/" + interfaceName + ":" + version);
        invocation.setAttachment(GROUP_KEY, group);
        invocation.setAttachment(VERSION_KEY, version);
        side = CommonConstants.CONSUMER;
        invocation.setInvoker(new TestMetricsInvoker(side));
        invocation.setTargetServiceUniqueName(group + "/" + interfaceName + ":" + version);
        RpcContext.getServiceContext()
                .setUrl(URL.valueOf("test://test:11/test?accesslog=true&group=dubbo&version=1.1&side=" + side));
    }

    @AfterEach
    public void teardown() {
        applicationModel.destroy();
    }

    @Test
    void testRequestsMetrics() {
        String applicationName = applicationModel.getApplicationName();

        defaultCollector.setApplicationName(applicationName);

        metricsFilter.invoke(new TestMetricsInvoker(side), invocation);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AppResponse mockRpcResult = new AppResponse();
        mockRpcResult.setException(new RpcException(RpcException.NETWORK_EXCEPTION));
        Result result = AsyncRpcResult.newDefaultAsyncResult(mockRpcResult, invocation);
        metricsFilter.onResponse(result, new TestMetricsInvoker(side), invocation);

        List<MetricSample> samples = collector.collect();
        for (MetricSample sample : samples) {
            Map<String, String> tags = sample.getTags();

            Assertions.assertEquals(tags.get(TAG_INTERFACE_KEY), interfaceName);
            Assertions.assertEquals(tags.get(TAG_METHOD_KEY), methodName);
            Assertions.assertEquals(tags.get(TAG_GROUP_KEY), group);
            Assertions.assertEquals(tags.get(TAG_VERSION_KEY), version);
        }

        samples = collector.collect();

        @SuppressWarnings("rawtypes")
        Map<String, Long> sampleMap = samples.stream()
                .collect(Collectors.toMap(MetricSample::getName, k -> ((GaugeMetricSample) k).applyAsLong()));

        Assertions.assertEquals(sampleMap.get(MetricsKey.METRIC_REQUESTS_NETWORK_FAILED_AGG.getNameByType(side)), 1L);
        Assertions.assertTrue(sampleMap.containsKey(MetricsKey.METRIC_QPS.getNameByType(side)));
    }

    public static class TestRequestEvent extends RequestEvent {
        private long rt;

        public TestRequestEvent(ApplicationModel applicationModel, TypeWrapper typeWrapper) {
            super(applicationModel, typeWrapper);
        }

        public void setRt(long rt) {
            this.rt = rt;
        }

        @Override
        public TimePair getTimePair() {
            return new TestTimePair(rt);
        }
    }

    public static class TestTimePair extends TimePair {

        long rt;

        public TestTimePair(long rt) {
            super(rt);
            this.rt = rt;
        }

        @Override
        public long calc() {
            return this.rt;
        }
    }
}
