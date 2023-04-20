package com.lijiawei.practice.mymall.learning.init;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

// 反射没别的 就是敲代码 实验
// 本次练习 通过反射获取一个对象的类型(考虑泛型因素)
public class ReflectTest1 {

    private String v1;

    private Integer[] v2;

    private List<String> v3;

    private List<List<String>> v4;

    @Test
    public void test1() throws NoSuchFieldException {
        Field field = ReflectTest1.class.getField(v1);
        Class<?> type = field.getType();
        //
    }
}
