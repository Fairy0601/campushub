package com.campushub.community.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * ClassName: CommunityUtil
 * Package: com.campushub.community.util
 * Description:用于用户注册时的工具类
 *
 * @Author 欣欣欣
 * @Create 2025/2/24 15:33
 * @Version 1.0
 */
public class CommunityUtil {
    /**
     * 生成随机字符串
     * 用于发送到邮箱的用户激活码+生成随机字符串
     * @return
     */
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * MD5加密，用于对用户密码加密，提高安全性
     * @param key
     * @return
     */
    public static String md5(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());//用于为密码提高安全性，在原有密码的基础上加一串随机动态码，例如：hello -> abc123def45 hello + 3e4a8 -> abc123def456abc
    }

    /**
     * 将服务器向浏览器返回的json数据转换成字符串
     * @param code
     * @param msg
     * @param map
     * @return
     */
    public static String getJSONString(int code, String msg, Map<String, Object> map) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        if (map != null) {
            for (String key : map.keySet()) {
                json.put(key, map.get(key));
            }
        }
        return json.toJSONString();
    }

    public static String getJSONString(int code, String msg) {
        return getJSONString(code, msg, null);
    }

    public static String getJSONString(int code) {
        return getJSONString(code, null, null);
    }

    //-----------------------测试----------------------------
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", 25);
        System.out.println(getJSONString(0, "ok", map));
    }
}
