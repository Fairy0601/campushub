package com.campushub.community.entity;

import lombok.Data;

import java.util.Date;

/**
 * ClassName: Comment
 * Package: com.campushub.community.entity
 * Description:
 *
 * @Author 欣欣欣
 * @Create 2025/3/2 16:13
 * @Version 1.0
 */
@Data
public class Comment {
    private int id;
    private int userId;
    private int entityType; //决定评论对象：帖子、帖子下的评论
    private int entityId; //评论的帖子的id（评论） 或者 评论的评论的id（回复）
    private int targetId; //只发生在回复里，即回复的目标用户
    private String content;
    private int status;
    private Date createTime;
}
