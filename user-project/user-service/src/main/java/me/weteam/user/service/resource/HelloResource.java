/*
 * Copyright (c) 2018. WeTeam Inc. All Rights Reserved.
 *
 */

package me.weteam.user.service.resource;

import lombok.extern.slf4j.Slf4j;
import me.weteam.user.client.api.HelloClient;
import me.weteam.user.service.loghub.AliLogHub;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    @Value("${sxw.config.message:this-is-a-message}")
    private String alibabaHello;

    @Autowired
    private AliLogHub aliyunLogHub;

    @Override
    public String hello() {
        return "Hello World.";
    }

    @Override
    public String alibaba() {
        return alibabaHello;
    }

    @Override
    public String aliyunLog() {
        String response = RandomStringUtils.randomAlphabetic(8);
        Map<String, String> logMap = new HashMap<>(8);
        logMap.put("trace_id", UUID.randomUUID().toString());
        logMap.put("server_name", "user-service");
        logMap.put("url", "HelloClient.aliyunLog");
        logMap.put("ip", "");
        logMap.put("duration", "102");
        logMap.put("user_id", "user_id");
        logMap.put("app", "app");
        logMap.put("client", "Client");
        logMap.put("platform", "platform");
        logMap.put("user_type", "");
        aliyunLogHub.send(logMap);
        return response;
    }


}
