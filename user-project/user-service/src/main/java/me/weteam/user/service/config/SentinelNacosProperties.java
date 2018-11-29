/*
 * Copyright (c) 2018. WeTeam Inc. All Rights Reserved.
 *
 */

package me.weteam.user.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.alibaba.sentinel.SentinelConstants;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * Sentinel Nacos Properties
 *
 * @author LarryKoo (larrykoo@126.com)
 * @date 2018/11/29 11:14
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = SentinelConstants.PROPERTY_PREFIX + "." + "datasource.nacos")
public class SentinelNacosProperties {

    /**
     * enable sentinel nacos auto configure, the default value is true
     */
    private boolean enabled = true;

    /**
     * nacos config server address
     */
    private String serverAddr;

    /**
     * namespace, separation configuration of different environments.
     */
    private String namespace;

    /**
     * nacos config group, group is config data meta info.
     */
    private String group = "DEFAULT_GROUP";

    /**
     * rule dataIds for sentinel
     */
    private RuleDataId dataId = new RuleDataId();

    @Data
    public static class RuleDataId {
        /**
         * nacos config flow rule dataId
         */
        private String flowRule;

        /**
         * nacos config param rule dataId
         */
        private String paramRule;
    }


}
