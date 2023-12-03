package springboot;

import org.springframework.stereotype.Service;

@Service
public class Me1 {

    public Me1(Me me) {
        System.out.println(me);
    }

    public void sleep() {
        System.out.println("\n睡觉！不休息哪里有力气学习！\n");  
    }  
} 