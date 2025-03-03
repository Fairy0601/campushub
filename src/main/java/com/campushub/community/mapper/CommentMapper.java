package com.campushub.community.mapper;

import com.campushub.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: CommentMapper
 * Package: com.campushub.community.mapper
 * Description:评论
 *              显示评论
 *                    - 根据实体查询一页评论数据
 *                    - 根据实体查询评论的数量
 *
 * @Author 欣欣欣
 * @Create 2025/3/2 16:14
 * @Version 1.0
 */
@Mapper
public interface CommentMapper {
    /**
     * 根据实体查询一页评论数据
     * @param entityType
     * @param entityId
     * @param offset
     * @param limit
     * @return
     */
    List<Comment> selectCommentsByEntity(int entityType, int entityId, int offset, int limit);

    /**
     * 根据实体查询评论的数量
     * @param entityType
     * @param entityId
     * @return
     */
    int selectCountByEntity(int entityType, int entityId);

    /**
     * 添加评论
     * @param comment
     * @return
     */
    int insertComment(Comment comment);

}
