/*
 * Copyright (c) 2019. WeTeam Inc. All Rights Reserved.
 *
 */

package me.weteam.user.service.loghub;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.io.Serializable;

/**
 * AliyunLog Properties 配置项
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2019-05-10 20:07
 * @since 3.0.0
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "aliyun.log")
public class AliLogProperties implements Serializable {
    private static final long serialVersionUID = 3255246417921427277L;

    /**
     * 是否开启 Aliyun Log Producer
     */
//    private boolean enabled = false;

    /**
     * Aliyun Log Project Name
     */
    private String project;

    /**
     * Aliyun Log Project endpoint area
     */
    private String endpoint;

    /**
     * Aliyun accessKeyId
     */
    private String accessKeyId;

    /**
     * Aliyun accessKeySecret
     */
    private String accessKeySecret;

    /**
     * Aliyun Log Project LogStore
     */
    private String logStore;

}
