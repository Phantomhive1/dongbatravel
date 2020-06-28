package com.tedu.pj.sys.dao;

import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

import java.util.Base64;
import java.util.UUID;

public class MD5Test {

    @Test
    void testMD502() { //盐值加密
        String s1 = "123456";
        String salt = UUID.randomUUID().toString();
        System.out.println(s1 + salt);
        String hex = DigestUtils.md5DigestAsHex((s1 + salt).getBytes());
        System.out.println(hex);
    }

    @Test
    void testBase64() {
        String s1 = "123456";
        String s2 = new String(Base64.getEncoder().encode(s1.getBytes()));
        System.out.println("s2= "+s2);
        String s3 = new String(Base64.getDecoder().decode(s2.getBytes()));
        System.out.println("s3= "+s3);
    }
}
