package com.zzp.learn.walklown.jarkata.jackson;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, visible = true)
//@JsonSubTypes(@JsonSubTypes.Type(value = Page.class, name = "input"))
public abstract class AbstractPage {

    private Integer offset;
}
