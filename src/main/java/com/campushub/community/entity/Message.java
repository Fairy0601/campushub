package com.campushub.community.entity;

import lombok.Data;

import java.util.Date;

/**
 * ClassName: Message
 * Package: com.campushub.community.entity
 * Description:
 *
 * @Author 欣欣欣
 * @Create 2025/3/3 11:13
 * @Version 1.0
 */
@Data
public class Message {
    private int id;
    private int fromId;
    private int toId;
    private String conversationId;
    private String content;
    private int status;
    private Date createTime;
}
