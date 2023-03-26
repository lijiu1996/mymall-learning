package com.lijiawei.practice.mymall.learning.init.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Slf4j
public class TaskConfig {

    // 添加订单超时取消任务  cron表达式 秒 分 时 天 月 星期
    @Scheduled(cron = "0 0/10 * ? * ?")
    private void cancelTimeOutOrder() {
        // TODO: 此处应调用取消订单的方法
        log.info("取消订单，并根据订单编号释放锁定库存");
    }
}
