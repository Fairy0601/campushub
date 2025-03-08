package com.campushub.community.event;

import com.alibaba.fastjson.JSONObject;
import com.campushub.community.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * ClassName: EventProducer
 * Package: com.campushub.community.event
 * Description:生产者类
 *
 * @Author 欣欣欣
 * @Create 2025/3/6 17:30
 * @Version 1.0
 */
@Component
public class EventProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    // 处理事件
    public void fireEvent(Event event) {
        // 将事件发布到指定的主题
        kafkaTemplate.send(event.getTopic(), JSONObject.toJSONString(event));
    }
}
