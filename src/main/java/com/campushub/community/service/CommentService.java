package com.campushub.community.service;

import com.campushub.community.entity.Comment;
import com.campushub.community.mapper.CommentMapper;
import com.campushub.community.util.CommunityConstant;
import com.campushub.community.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * ClassName: CommentService
 * Package: com.campushub.community.service
 * Description:
 *
 * @Author 欣欣欣
 * @Create 2025/3/2 16:32
 * @Version 1.0
 */
@Service
public class CommentService implements CommunityConstant {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    public List<Comment> findCommentsByEntity(int entityType, int entityId, int offset, int limit){
        return commentMapper.selectCommentsByEntity(entityType, entityId, offset, limit);
    }

    public int findCommentCount(int entityType, int entityId) {
        return commentMapper.selectCountByEntity(entityType, entityId);
    }

    /**
     * 添加评论
     * @param comment
     * @return
     */
    //因为方法中存在两个DML语句，为了保证这两次对数据库的操作同时成功或者失败，进行声明式事务
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int addComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }

        // 添加评论（HTML转义；过滤敏感词）
        comment.setContent(HtmlUtils.htmlEscape(comment.getContent()));
        comment.setContent(sensitiveFilter.filter(comment.getContent()));
        //将评论插⼊到数据库comment表中，并返回增加成功⾏数rows
        int rows = commentMapper.insertComment(comment);

        // 更新帖子评论数量
        if (comment.getEntityType() == ENTITY_TYPE_POST) {
            //查询数据库comment表中帖⼦评论的数量
            int count = commentMapper.selectCountByEntity(comment.getEntityType(), comment.getEntityId());
            //将评论数量更新到discuss_post表⾥⾯
            discussPostService.updateCommentCount(comment.getEntityId(), count);
        }

        //最后返回增加成功⾏数rows
        return rows;
    }

    /**
     * 根据id查询评论
     * @param id
     * @return
     */
    public Comment findCommentById(int id) {
        return commentMapper.selectCommentById(id);
    }
}
