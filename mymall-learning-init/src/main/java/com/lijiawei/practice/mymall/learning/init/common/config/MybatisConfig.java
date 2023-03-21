package com.lijiawei.practice.mymall.learning.init.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.lijiawei.practice.mymall.learning.init.brand.mapper")
public class MybatisConfig {
}
