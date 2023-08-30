;

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.stereotype.Service;

@Service
public class Me {

    @JsonProperty(required = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sleep() {
        System.out.println("\n睡觉！不休息哪里有力气学习！\n");
    }
}