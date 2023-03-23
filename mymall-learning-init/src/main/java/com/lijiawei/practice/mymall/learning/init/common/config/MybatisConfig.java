package com.lijiawei.practice.mymall.learning.init.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = {
        "com.lijiawei.practice.mymall.learning.init.brand.mapper",
        "com.lijiawei.practice.mymall.learning.init.user.mapper"
})
public class MybatisConfig {
}
