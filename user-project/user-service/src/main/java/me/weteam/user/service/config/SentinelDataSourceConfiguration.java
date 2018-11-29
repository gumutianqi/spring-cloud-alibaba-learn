/*
 * Copyright (c) 2018. WeTeam Inc. All Rights Reserved.
 *
 */

package me.weteam.user.service.config;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowItem;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Nacos DataSource
 *
 * @author LarryKoo (larrykoo@126.com)
 * @date 2018/11/29 10:42
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({SentinelNacosProperties.class})
public class SentinelDataSourceConfiguration {

    private SentinelNacosProperties properties;

    public SentinelDataSourceConfiguration(SentinelNacosProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void init() {
        log.info("=>> Load sentinel rules form Nacos config server.");
        loadFlowRules(properties.getServerAddr(), properties.getGroup(), properties.getDataId().getFlowRule());
        loadParamRules(properties.getServerAddr(), properties.getGroup(), properties.getDataId().getParamRule());
    }

    /**
     * 动态从 Nacos Config 加载 Sentinel flow-rules
     *
     * @param remoteAddress
     * @param groupId
     * @param dataId
     */
    private static void loadFlowRules(String remoteAddress, String groupId, String dataId) {
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource =
                new NacosDataSource<>(remoteAddress, groupId, dataId,
                        source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                        }));

        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
    }

    /**
     * 动态从 Nacos Config 加载 Sentinel param-rules
     *
     * @param remoteAddress
     * @param groupId
     * @param dataId
     */
    private static void loadParamRules(String remoteAddress, String groupId, String dataId) {
        ReadableDataSource<String, List<ParamFlowRule>> paramRuleDataSource =
                new NacosDataSource<>(remoteAddress, groupId, dataId,
                        source -> JSON.parseObject(source, new TypeReference<List<ParamFlowRule>>() {
                        }));

        ParamFlowItem item = new ParamFlowItem();

        ParamFlowRuleManager.register2Property(paramRuleDataSource.getProperty());
    }


}
