package com.walklown.attempt.spring.mysql.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@TableName(value = "t_user", autoResultMap = true)
public class UserDO implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

}
