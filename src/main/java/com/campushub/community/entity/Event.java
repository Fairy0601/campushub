package com.campushub.community.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: Event
 * Package: com.campushub.community.entity
 * Description:  - 封装事件对象
 * 发送系统消息---面向事件的操作，使用Kafka发布订阅模式
 *
 * @Author 欣欣欣
 * @Create 2025/3/6 16:48
 * @Version 1.0
 */
public class Event {
    private String topic; //事件的类型（点赞、评论、关注）
    private int userId;  //事件来源（触发的人）
    private int entityType;  //干了什么（点赞、评论、关注）
    private int entityId;  //时间发生在哪个实体上，实体id
    private int entityUserId;  //实体的作者
    private Map<String, Object> data = new HashMap<>(); //其他额外数据，具有扩展性

    public String getTopic() {
        return topic;
    }

    public Event setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Event setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getEntityType() {
        return entityType;
    }

    public Event setEntityType(int entityType) {
        this.entityType = entityType;
        return this;
    }

    public int getEntityId() {
        return entityId;
    }

    public Event setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public int getEntityUserId() {
        return entityUserId;
    }

    public Event setEntityUserId(int entityUserId) {
        this.entityUserId = entityUserId;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Event setData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
