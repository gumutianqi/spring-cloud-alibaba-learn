/*
 * Copyright (c) 2019. WeTeam Inc. All Rights Reserved.
 *
 */

package me.weteam.user.service.loghub;

import com.aliyun.openservices.aliyun.log.producer.LogProducer;
import com.aliyun.openservices.aliyun.log.producer.errors.LogSizeTooLargeException;
import com.aliyun.openservices.aliyun.log.producer.errors.ProducerException;
import com.aliyun.openservices.aliyun.log.producer.errors.TimeoutException;
import com.aliyun.openservices.log.common.LogItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 基于 Aliyun Log Producer 封装
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2019-05-10 20:37
 * @since 3.0.0
 */
@Slf4j
@Component
public class AliLogHub {

    private final LogProducer getLogProducer;
    private final AliLogProperties properties;

    public AliLogHub(LogProducer getLogProducer, AliLogProperties properties) {
        this.getLogProducer = getLogProducer;
        this.properties = properties;
    }

    /**
     * 发送一个 K/V 日志对象
     *
     * @param logMap
     */
    public void send(Map<String, String> logMap) {
        this.send("", "", logMap);
    }

    /**
     * 发送一个 K/V 日志对象
     *
     * @param logMap 日志对象
     */
    public void send(String topic, String source, Map<String, String> logMap) {
        LogItem logItem = new LogItem();
        logMap.forEach(logItem::PushBack);
        try {
            getLogProducer.send(properties.getProject(), properties.getLogStore(),
                    topic, source, logItem);
        } catch (InterruptedException e) {
            log.warn("The current thread has been interrupted during send logs.");
        } catch (ProducerException e) {
            if (e instanceof LogSizeTooLargeException) {
                log.error("The size of log is larger than the maximum allowable size", e);
            } else if (e instanceof TimeoutException) {
                log.error("The time taken for allocating memory for the logs has surpassed.", e);
            } else {
                log.error("Failed to send log, logItem={}", logItem, e);
            }
        } catch (Exception e) {
            log.error("Failed to send log, logItem={}", logItem, e);
        }
    }


}
