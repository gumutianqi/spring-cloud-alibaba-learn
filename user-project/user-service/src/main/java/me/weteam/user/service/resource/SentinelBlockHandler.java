/*
 * Copyright (c) 2018. WeTeam Inc. All Rights Reserved.
 *
 */

package me.weteam.user.service.resource;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO 源代码描述信息
 *
 * @author LarryKoo (larrykoo@126.com)
 * @date 2018/11/26 12:12
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
@Slf4j
public class SentinelBlockHandler {

    /**
     * 熔断限流异常处理
     */
    public static String handleException(BlockException ex) {
        ex.printStackTrace();

        log.info("Oops, error occurred" + ex.getClass().getCanonicalName());

        return "Oops, error occurred" + ex.getClass().getCanonicalName();
    }

    /**
     * 熔断限流异常处理
     */
    public static String handleException(BlockException ex, String id) {
        ex.printStackTrace();

        log.info("[{}]Oops, error occurred" + ex.getClass().getCanonicalName(), id);

        return "[" + id + "]Oops, error occurred" + ex.getClass().getCanonicalName();
    }
}
