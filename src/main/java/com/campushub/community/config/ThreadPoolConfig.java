package com.campushub.community.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * ClassName: ThreadPoolConfig
 * Package: com.campushub.community.config
 * Description: - Spring线程池配置类
 *
 * @Author 欣欣欣
 * @Create 2025/3/10 15:47
 * @Version 1.0
 */
@Configuration
@EnableScheduling
@EnableAsync
public class ThreadPoolConfig {
}
