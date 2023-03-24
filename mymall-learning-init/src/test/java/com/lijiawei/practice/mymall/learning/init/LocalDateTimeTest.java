package com.lijiawei.practice.mymall.learning.init;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author Li JiaWei
 * @ClassName: LocalDateTimeTest
 * @Description:
 * @Date: 2023/3/20 13:42
 * @Version: 1.0
 */
@SpringBootTest
public class LocalDateTimeTest {

    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void test() {
        String password = "test";
        System.out.println(encoder.encode(password));

        System.out.println(encoder.encode(password));

        System.out.println();
    }
}
