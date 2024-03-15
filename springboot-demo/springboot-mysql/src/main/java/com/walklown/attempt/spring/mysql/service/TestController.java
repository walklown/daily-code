package com.walklown.attempt.spring.mysql.service;

import com.walklown.attempt.spring.mysql.db.UserDO;
import com.walklown.attempt.spring.mysql.db.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{id}")
    public UserDO home(@PathVariable Integer id) {
        return userMapper.selectById(id);
    }
}
