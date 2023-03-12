package com.zzp.learn.springboot;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Orange extends Fruit {

    private String price;
}