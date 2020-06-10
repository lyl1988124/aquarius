package com.lyl.aquarius.shiro;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author lyl
 * @Package com.lyl.aquarius.shiro
 * @Title: ShiroPasswordUtils
 * @Description:
 * @date 2020/6/10 21:38
 */
public class ShiroPasswordUtils {

    public static final String ALGORITHM_NAME_MD5 = "MD5";

    public static final Integer HASH_ITERATIONS = 10;

    public static final Integer SALT_LENGTH = 8;

    /**
     * 生成盐
     * @return salt
     */
    public static String generateSalt() {
        return RandomStringUtils.randomAlphanumeric(SALT_LENGTH);
    }

    /**
     * 生成密码
     * @param password password
     * @param salt salt
     * @return password
     */
    public static String generatePassword(String password, String salt) {
        SimpleHash simpleHash = new SimpleHash(ALGORITHM_NAME_MD5,password,salt,HASH_ITERATIONS);
        return simpleHash.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateSalt());
        System.out.println(generatePassword("password","HO5Wr5sf"));
    }
}
