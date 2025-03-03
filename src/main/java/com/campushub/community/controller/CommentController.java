package com.campushub.community.controller;

import com.campushub.community.entity.Comment;
import com.campushub.community.service.CommentService;
import com.campushub.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * ClassName: CommentController
 * Package: com.campushub.community.controller
 * Description:
 *
 * @Author 欣欣欣
 * @Create 2025/3/3 10:25
 * @Version 1.0
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private HostHolder hostHolder;

    /**
     * 添加评论
     * @param discussPostId
     * @param comment
     * @return
     */
    @PostMapping (path = "/add/{discussPostId}")
    public String addComment(@PathVariable("discussPostId") int discussPostId, Comment comment) {
        comment.setUserId(hostHolder.getUser().getId());
        comment.setStatus(0);
        comment.setCreateTime(new Date());
        commentService.addComment(comment);

        return "redirect:/discuss/detail/" + discussPostId;
    }
}
