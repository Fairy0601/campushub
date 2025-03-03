package com.campushub.community.service;

import com.campushub.community.entity.Message;
import com.campushub.community.mapper.MessageMapper;
import com.campushub.community.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * ClassName: MessageService
 * Package: com.campushub.community.service
 * Description:
 *
 * @Author 欣欣欣
 * @Create 2025/3/3 11:39
 * @Version 1.0
 */
@Service
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    /**
     * 获取对应用户userId的会话列表
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    public List<Message> findConversations(int userId, int offset, int limit) {
        return messageMapper.selectConversations(userId, offset, limit);
    }

    /**
     * 获取对应用户userId的会话数量
     * @param userId
     * @return
     */
    public int findConversationCount(int userId) {
        return messageMapper.selectConversationCount(userId);
    }

    /**
     * 获取对应会话conversationId所包含的私信列表.
     * @param conversationId
     * @param offset
     * @param limit
     * @return
     */
    public List<Message> findLetters(String conversationId, int offset, int limit) {
        return messageMapper.selectLetters(conversationId, offset, limit);
    }

    /**
     * 获取对应会话conversationId的私信数量
     * @param conversationId
     * @return
     */
    public int findLetterCount(String conversationId) {
        return messageMapper.selectLetterCount(conversationId);
    }

    /**
     * 获取对应用户userId的未读消息数，
     *      若conversationId为空，则表示用户的总未读消息数
     *      若conversationId不为空，为当前conversationId会话的未读消息数
     * @param userId
     * @param conversationId
     * @return
     */
    public int findLetterUnreadCount(int userId, String conversationId) {
        return messageMapper.selectLetterUnreadCount(userId, conversationId);
    }

    /**
     * 添加私信
     * @param message
     * @return
     */
    public int addMessage(Message message){
        message.setContent(HtmlUtils.htmlEscape(message.getContent()));
        message.setContent(sensitiveFilter.filter(message.getContent()));
        return messageMapper.insertMessage(message);
    }

    /**
     * 已读标记
     * @param ids
     * @return
     */
    public int readMessage(List<Integer> ids){
        return messageMapper.updateStatus(ids, 1);
    }
}
