package com.campushub.community.controller;

import com.campushub.community.entity.DiscussPost;
import com.campushub.community.entity.Page;
import com.campushub.community.entity.User;
import com.campushub.community.service.DiscussPostService;
import com.campushub.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: HomeController
 * Package: com.campushub.community.controller
 * Description:
 *
 * @Author 欣欣欣
 * @Create 2025/2/13 19:44
 * @Version 1.0
 */
@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private DiscussPostService discussPostService;

    //http://localhost:8080/campushub/community/index
    @GetMapping("/index")
    public String getIndexPage(Model model, Page page){
        // 方法调用钱,SpringMVC会自动实例化Model和Page,并将Page注入Model.
        // 所以,在thymeleaf中可以直接访问Page对象中的数据.

        //获取并设置总页数
        page.setRows(discussPostService.findDiscussPostRows(0));
        //设置查询路径
        page.setPath("/index");

        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if(list != null){
            for(DiscussPost post : list){
                Map<String, Object> map = new HashMap<>();
                map.put("post" , post);
                User user = userService.findUserById(post.getUserId());//使用post对应的userId查询对应的user信息
                map.put("user", user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPosts);
        return "/index";
    }

    /**
     * 获取错误页面
     * @return
     */
    @GetMapping("/error")
    public String getErrorPage(){
        return "/error/500";
    }
}
