package com.lijiawei.practice.mymall.learning.init.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.lijiawei.practice.mymall.learning.init.brand.mapper")
public class MybatisConfig {
}
