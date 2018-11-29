/*
 * Copyright (c) 2018. WeTeam Inc. All Rights Reserved.
 *
 */

package me.weteam.user.service.resource;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sentinel Example Controller
 *
 * @author LarryKoo (larrykoo@126.com)
 * @date 2018/11/26 12:08
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class SentinelController {

    @GetMapping("/sentinel")
    @SentinelResource(value = "sentinelFlow", blockHandlerClass = {SentinelBlockHandler.class}, blockHandler = "handleException")
    public String sentinelFlowTest() {
        log.info("sentinel-is-work.");

        JSONObject obj = new JSONObject();
        obj.put("msg", "OK");
        obj.put("code", 200);

        return obj.toJSONString();
    }

    @GetMapping("/sentinel_hot")
    @SentinelResource(value = "sentinelParam", blockHandlerClass = {SentinelBlockHandler.class}, blockHandler = "handleException")
    public  String sentinelParamTest(@RequestParam("id") String id) {
        log.info("[{}]sentinel_hot-is-work.", id);

        JSONObject obj = new JSONObject();
        obj.put("msg", "OK");
        obj.put("code", 200);

        return obj.toJSONString();
    }


}
