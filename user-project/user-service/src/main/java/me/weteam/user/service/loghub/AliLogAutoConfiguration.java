/*
 * Copyright (c) 2019. WeTeam Inc. All Rights Reserved.
 *
 */

package me.weteam.user.service.loghub;

import com.aliyun.openservices.aliyun.log.producer.LogProducer;
import com.aliyun.openservices.aliyun.log.producer.ProducerConfig;
import com.aliyun.openservices.aliyun.log.producer.ProjectConfig;
import com.aliyun.openservices.aliyun.log.producer.ProjectConfigs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * LogHub 工具，构建 Log 采集对象
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2019-05-10 20:04
 * @since 3.0.0
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(AliLogProperties.class)
public class AliLogAutoConfiguration {
    private AliLogProperties properties;

    public AliLogAutoConfiguration(AliLogProperties properties) {
        this.properties = properties;
    }

    /**
     * 创建 LogProducer 对象
     *
     * @return
     */
    @Bean
    @ConditionalOnClass(LogProducer.class)
    public LogProducer getLogProducer() {
        ProducerConfig producerConfig = new ProducerConfig(projectConfigs());
        producerConfig.setRetries(3);
        LogProducer producer = new LogProducer(producerConfig);
        return producer;
    }

    @Bean
    @ConditionalOnClass(ProjectConfigs.class)
    public ProjectConfigs projectConfigs() {
        ProjectConfigs projectConfigs = new ProjectConfigs();
        projectConfigs.put(projectConfig());
        return projectConfigs;
    }

    @Bean
    @ConditionalOnClass(ProjectConfig.class)
    public ProjectConfig projectConfig() {
        return new ProjectConfig(properties.getProject(),
                properties.getEndpoint(),
                properties.getAccessKeyId(),
                properties.getAccessKeySecret());
    }
}
