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
    private int id; //评论的id，⾃增主键
    private int userId; //用户id
    private int entityType; //决定评论对象：帖子、帖子下的评论，评论的实体类型，0：帖⼦；1：评论
    private int entityId; //评论的帖子的id（评论） 或者 评论的评论的id（回复）评论的具体⽬标的id，如果评论的是帖⼦，就是该帖⼦的id;
                            //如果评论的是别⼈的评论，那么就是此条评论的id
    private int targetId; //只发生在回复里，即回复的目标用户  评论的⽬标用户的id,对哪个用户评论，就是哪个用户的id
    private String content; //评论内容
    private int status; //评论的状态，0：有效评论；1：⽆效评论（已删除的）
    private Date createTime; //创建时间
}
