package com.lijiawei.practice.mymall.learning.init.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

@Data
@Slf4j
@Component
public class JWTUtil {

    @Value("${jwt.secret:secret}")
    private String secret;
    @Value("${jwt.expiration:3600000}") // 默认有效期一个小时
    private Long expiration;
    @Value("${jwt.tokenHeader:Authorization}")
    private String tokenHeader;
    @Value("${jwt.tokenHead:Bearer}")
    private String tokenHead;

    public String getUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    //业务定制方法
    public String createJWTByUserId(Long id) {
        String jsonString = JSONUtil.obj2String(Map.of("id", id));
        return createJWT(jsonString);
    }

    public Long getUserIdFromJWTToken(String jwtToken) {
        Long userId = null;
        try {
            String jwtInfo = parseJWTInfo(jwtToken);
            if (jwtInfo != null) {
                HashMap<String,Long> map = JSONUtil.string2Obj(jwtInfo, HashMap.class);
                if (map != null)
                    userId = map.get("id");
            }
        } catch (Exception e) {
            throw new RuntimeException("JWT解析失败",e);
        }
        return userId;
    }

    //通用方法

    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public String createJWT(String jsonSubject) {
        JwtBuilder builder = getJwtBuilder(jsonSubject, null, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());// 设置过期时间
        return builder.compact();
    }

    public  String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }


    private JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis=expiration;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // token存放的内容  目前采用JSON数据
                .setIssuer("hello")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    /**
     * 对密钥secret进行加密
     * @return
     */
    public SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(secret);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    public Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public String parseJWTInfo(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody().getSubject();
    }

}
