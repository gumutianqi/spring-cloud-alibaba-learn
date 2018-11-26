/*
 * Copyright (c) 2018. WeTeam Inc. All Rights Reserved.
 *
 */

package me.weteam.user.service;

import com.spring4all.swagger.EnableSwagger2Doc;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * Service Application Main
 *
 * @author LarryKoo (larrykoo@126.com)
 * @date 2018/11/23 11:21
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
@EnableSwagger2Doc
@EnableDiscoveryClient
@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    /**
     * prometheus metrics
     *
     * @return
     */
    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", "user-service");
    }

}
