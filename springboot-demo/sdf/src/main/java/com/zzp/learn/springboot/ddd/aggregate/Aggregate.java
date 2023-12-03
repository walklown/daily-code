package springboot;

import com.zzp.learn.springboot.ddd.aggregate.IAggregate;
import com.zzp.learn.springboot.ddd.aggregate.share.DBClient;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Component
public class Aggregate implements InitializingBean, IAggregate {

    @Resource
    private DBClient dbClient;

    public Aggregate() {
    }

    public void log() {
        log.info("dbClient:{}", dbClient);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("afterPropertiesSet");
    }
}
