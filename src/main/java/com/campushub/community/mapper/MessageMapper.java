package com.campushub.community.mapper;

import com.campushub.community.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import sun.misc.resources.Messages_es;

import java.util.List;

/**
 * ClassName: MessageMapper
 * Package: com.campushub.community.mapper
 * Description:
 *
 * @Author 欣欣欣
 * @Create 2025/3/3 11:14
 * @Version 1.0
 */
@Mapper
public interface MessageMapper {
    // 查询当前用户的会话列表,针对每个会话只返回一条最新的私信.
    List<Message> selectConversations(int userId, int offset, int limit);

    // 查询当前用户的会话数量.
    int selectConversationCount(int userId);

    // 查询某个会话所包含的私信列表.
    List<Message> selectLetters(String conversationId, int offset, int limit);

    // 查询某个会话所包含的私信数量.
    int selectLetterCount(String conversationId);

    // 查询未读私信的数量
    int selectLetterUnreadCount(int userId, String conversationId);

    //新增消息
    int insertMessage(Message message);

    //更新消息状态(用于设置已读)
    int updateStatus(List<Integer> ids, int status);
}
