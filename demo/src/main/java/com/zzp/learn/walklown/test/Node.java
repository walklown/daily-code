package com.zzp.learn.walklown.test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Node<T> {

    private String id;

    private String name;

    private T child;
}
