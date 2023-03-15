//package com.zzp.learn.netty;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//public class DefaultServerProvider implements NextProtoNego.ServerProvider {
//    private static final List<String> PROTOCOLS =
//            Collections.unmodifiableList(Arrays.asList("spdy/2", "spdy/3", "http/1.1"));  //1
//
//    private String protocol;
//
//    @Override
//    public void unsupported() {
//        protocol = "http/1.1";   //2
//    }
//
//    @Override
//    public List<String> protocols() {
//        return PROTOCOLS;   //3
//    }
//
//    @Override
//    public void protocolSelected(String protocol) {
//        this.protocol = protocol;  //4
//    }
//
//    public String getSelectedProtocol() {
//        return protocol;  //5
//    }
//}