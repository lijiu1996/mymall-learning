package com.lijiawei.practice.mymall.learning.init.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
public class JSONUtil {

    private static ObjectMapper objectMapper ;

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private static final String TIME_FORMAT = "HH:mm:ss";

    static
    {
        objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        //序列化
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT)));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(TIME_FORMAT)));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
        //反序列化
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_FORMAT)));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));

        objectMapper.registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(javaTimeModule);

        // 设置date日期格式
        objectMapper.setDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));
        //序列化的时候序列对象的那些属性
        //JsonInclude.Include.NON_DEFAULT 属性为默认值不序列化
        //JsonInclude.Include.ALWAYS      所有属性都序列化
        //JsonInclude.Include.NON_EMPTY   属性为 空（“”） 或者为 NULL 不序列化
        //JsonInclude.Include.NON_NULL    属性为NULL 不序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //反序列化时,遇到未知属性会不会报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        // 如果是空对象的时候,不抛异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 忽略 transient 修饰的属性
        objectMapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);

    }

    public static <T> String obj2String(T obj) {
        if (obj == null)
            return null;
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.warn("Jackson Parse Object to String Error : {}",e.getMessage());
            return null;
        }
    }

    public static <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.warn("Jackson Parse Object to String Error : {}",e.getMessage());
            return null;
        }
    }

    public static <T> T string2Obj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null)
            return null;
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            log.warn("Jackson Parse String to Object Error : {}",e.getMessage());
            return null;
        }
    }

    // 集合类转换
    public static <T> T string2Obj(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (IOException e) {
            log.warn("Jackson Parse String to Collection Error : {}",e.getMessage());
            return null;
        }
    }

    public static <T> T string2Obj(String str, Class<?> collectionClazz, Class<?>... elementClazzes) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClazz, elementClazzes);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
            log.warn("Jackson Parse String to Collection Error : {}",e.getMessage());
            return null;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Dept {
        Integer deptno;
        String  dname;
        String  loc;
    }

    @Test
    public void obj2String() {
        Dept dept = new Dept(10, "SALES", "CHICAGO");
        String res = JSONUtil.obj2String(dept);
        System.out.println(res);

        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        String res1 = JSONUtil.obj2String(list);
        System.out.println(res1);

        ArrayList<Dept> depts = new ArrayList<>();
        depts.add(new Dept(10,"ACCOUNTING","NEWYORK"));
        depts.add(new Dept(20,"RESEARCH","DALLAS"));
        depts.add(new Dept(30,"SALES","CHICAGO"));
        depts.add(new Dept(40,"OPERATIONS","BOSTON"));
        String res2 = JSONUtil.obj2String(depts);
        System.out.println(res2);
    }

    @Test
    public void obj2StringPretty() {
        Dept dept = new Dept(10, "SALES", "CHICAGO");
        String res = JSONUtil.obj2StringPretty(dept);
        System.out.println(res);
    }

    @Test
    public void string2Obj() {
        String json = "{\"deptno\":10,\"dname\":\"SALES\",\"loc\":\"CHICAGO\"}";
        Dept dept = JSONUtil.string2Obj(json, Dept.class);
        System.out.println(dept.getDname());
    }

    @Test
    public void testString2Obj() {
        String json1="[\"aaa\",\"bbb\",\"ccc\"]";
        List<String> list1 = JSONUtil.string2Obj(json1,new TypeReference<List<String>>() {});
        System.out.println(list1.get(1));

        String json2 ="[{\"deptno\":10,\"dname\":\"ACCOUNTING\",\"loc\":\"NEWYORK\"},{\"deptno\":20,\"dname\":\"RESEARCH\",\"loc\":\"DALLAS\"},{\"deptno\":30,\"dname\":\"SALES\",\"loc\":\"CHICAGO\"},{\"deptno\":40,\"dname\":\"OPERATIONS\",\"loc\":\"BOSTON\"}]";
        List<Dept> list2 = JSONUtil.string2Obj(json2,new TypeReference<List<Dept>>() {});
        System.out.println(list2.get(2).getDname());
    }

    @Test
    public void testString2Obj1() {
        String json ="[{\"deptno\":10,\"dname\":\"ACCOUNTING\",\"loc\":\"NEWYORK\"},{\"deptno\":20,\"dname\":\"RESEARCH\",\"loc\":\"DALLAS\"},{\"deptno\":30,\"dname\":\"SALES\",\"loc\":\"CHICAGO\"},{\"deptno\":40,\"dname\":\"OPERATIONS\",\"loc\":\"BOSTON\"}]";
        List<Dept> list = JSONUtil.string2Obj(json,List.class,Dept.class);
        System.out.println(list.get(2));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Province {
        private Long id;
        private String name;
        private String area;
        private Integer priority;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class AA {
        private Date date;
        private LocalTime localTime;
        private LocalDate localDate;
        private LocalDateTime localDateTime;
    }

    public static void main(String[] args) {
        AA aa = new AA(new Date(), LocalTime.now(), LocalDate.now(), LocalDateTime.now());

        String obj2String = obj2String(aa);
        System.out.println(obj2String);

        String obj2StringPretty = obj2StringPretty(aa);
        System.out.println(obj2StringPretty);

        String str = "{  \"date\" : \"2022-09-30 09:04:14\",  \"localTime\" : \"09:04:14\",  \"localDate\" : \"2022-09-30\",  \"localDateTime\" : \"2022-09-30 09:04:14\"}";
        AA obj = string2Obj(str, AA.class);
        System.out.println(obj);

        List<Province> provinceList = new ArrayList<>();
        provinceList.add(new Province(1001L, "aa", "aaaaaa", 11));
        provinceList.add(new Province(1002L, "bb", "bbbbbb", 22));
        provinceList.add(new Province(1003L, "cc", "cccccc", 33));
        provinceList.add(new Province(1004L, "dd", "dddddd", 44));

        String provinceListJson = obj2String(provinceList);
        System.out.println(provinceListJson);

        provinceListJson = "[\t{\t\t\"area\":\"aaaaaa\",\t\t\"name\":\"aa\",\t\t\"id\":1001,\t\t\"priority\":11\t},\t{\t\t\"area\":\"bbbbbb\",\t\t\"name\":\"bb\",\t\t\"id\":1002,\t\t\"priority\":22\t},\t{\t\t\"area\":\"cccccc\",\t\t\"name\":\"cc\",\t\t\"id\":1003,\t\t\"priority\":33\t},\t{\t\t\"area\":\"dddddd\",\t\t\"name\":\"dd\",\t\t\"id\":1004,\t\t\"priority\":44\t}]";
        List<Province> provinces = string2Obj(provinceListJson, new TypeReference<List<Province>>() {
        });
        provinces.forEach(System.out::println);

        System.out.println();

        provinces = string2Obj(provinceListJson, List.class, Province.class);
        provinces.forEach(System.out::println);

    }

    @Test
    public void simpleTest1() {
        String json = "{\"id\":3}";
        Map<String,Long> hashMap = string2Obj(json, new TypeReference<HashMap<String,Long>>() {});
        System.out.println("...");
    }
}
