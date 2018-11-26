/*
 * Copyright (c) 2018. WeTeam Inc. All Rights Reserved.
 *
 */

package me.weteam.user.service.resource;

import lombok.extern.slf4j.Slf4j;
import me.weteam.user.client.api.HelloClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello Resource
 *
 * @author LarryKoo (larrykoo@126.com)
 * @date 2018/11/23 17:42
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
@Slf4j
@Validated
@RefreshScope
@RestController
public class HelloResource implements HelloClient {
    /**
     * 从配置中心读取一个自定义配置，加上注解 @RefreshScope 可以实现动态刷新
     */
    @Value("${weteam.alibaba.hello:alibaba-is-good}")
    private String alibabaHello;

    @Override
    public String hello() {

        return "Hello World.";
    }

    @Override
    public String alibaba() {
        return alibabaHello;
    }
}
