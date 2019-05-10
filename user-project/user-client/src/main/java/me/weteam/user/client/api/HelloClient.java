/*
 * Copyright (c) 2018. WeTeam Inc. All Rights Reserved.
 *
 */

package me.weteam.user.client.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.weteam.user.client.AppServiceDefine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Hello client
 *
 * @author LarryKoo (larrykoo@126.com)
 * @date 2018/11/23 17:43
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
@Api(value = "Hello Client 演示接口", protocols = "application/json")
@FeignClient(value = AppServiceDefine.APP_SERVICE_NAME)
@RequestMapping(value = AppServiceDefine.MAPPING_API_PREFIX, produces = "application/json")
public interface HelloClient {
    /**
     * hello world example
     *
     * @return
     */
    @ApiOperation(value = "hello-world", notes = "hello world example", nickname = "HelloClient-hello")
    @GetMapping("/hello")
    String hello();

    /**
     * hello alibaba example
     *
     * @return
     */
    @ApiOperation(value = "hello-alibaba", notes = "hello alibaba example", nickname = "HelloClient-alibaba")
    @GetMapping("/alibaba")
    String alibaba();

    /**
     * aliyun log hub
     * @return
     */
    @ApiOperation(value = "aliyun-log-hub", nickname = "HelloClient-aliyunLog")
    @GetMapping("/aliyun_log")
    String aliyunLog();
}
