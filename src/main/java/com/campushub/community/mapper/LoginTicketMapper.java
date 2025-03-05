package com.campushub.community.mapper;

import com.campushub.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

/**
 * ClassName: LoginTicketMapper
 * Package: com.campushub.community.mapper
 * Description:登录凭证，访问数据库
 *
 * @Author 欣欣欣
 * @Create 2025/2/26 9:49
 * @Version 1.0
 */
@Mapper
@Deprecated
public interface LoginTicketMapper {
    /**
     * 将新的LoginTicket加入数据库中
     * @param loginTicket
     * @return
     */
    @Insert({
            "insert into login_ticket(user_id,ticket,status,expired) ",
            "values(#{userId},#{ticket},#{status},#{expired})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertLoginTicket(LoginTicket loginTicket);

    /**
     * 根据登录凭证ticket，获取相应的LoginTicket对象
     * @param ticket
     * @return
     */
    @Select({
            "select id,user_id,ticket,status,expired ",
            "from login_ticket where ticket=#{ticket}"
    })
    LoginTicket selectByTicket(String ticket);

    /**
     * 根据登录凭证ticket，修改status
     * 演示了一下使用注解的方法写sql语句时，如何写动态sql语句
     * @param ticket
     * @param status
     * @return
     */
    @Update({
            "<script>",
            "update login_ticket set status=#{status} where ticket=#{ticket} ",
            "<if test=\"ticket!=null\"> ",
            "and 1=1 ",
            "</if>",
            "</script>"
    })
    int updateStatus(String ticket, int status);
}
