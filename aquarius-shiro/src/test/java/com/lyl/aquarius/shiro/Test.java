package com.lyl.aquarius.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author lyl
 * @Package com.lyl.aquarius.shiro
 * @Title: Test
 * @Description:
 * @date 2020/5/7 22:24
 */
public class Test {
    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123","111",2);
        System.out.println(md5Hash.toHex());
        System.out.println(md5Hash.toString());
    }
}
