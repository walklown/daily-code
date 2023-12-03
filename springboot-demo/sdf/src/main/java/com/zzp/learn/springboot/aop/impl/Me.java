package springboot;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zzp.learn.springboot.aop.impl.Sleepable;
import org.springframework.stereotype.Service;

@Service
public class Me implements Sleepable {

    @JsonProperty(required = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void sleep() {
        System.out.println("\n睡觉！不休息哪里有力气学习！\n");  
    }  
} 