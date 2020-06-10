package com.lyl.aquarius.shiro;

import com.lyl.aquarius.controller.UserController;
import com.lyl.aquarius.controller.entity.User;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lyl
 * @Package com.lyl.aquarius.shiro
 * @Title: Aquarius
 * @Description:
 * @date 2020/4/29 22:06
 */
public class AquariusRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(AquariusRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("AuthorizationInfo doGetAuthorizationInfo");
        //获取用户名
        User user = (User) principalCollection.getPrimaryPrincipal();

        //此处从数据库获取该用户的角色
        Set<String> roles = Stream.of("admin", "user").collect(Collectors.toSet());
        //此处从数据库获取该角色的权限
        Set<String> permissions = Stream.of("permission1", "permission2").collect(Collectors.toSet());
        //放到info里返回
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permissions);
        info.setRoles(roles);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
        throws AuthenticationException {
        logger.info("doGetAuthenticationInfo");
        String account = (String) authenticationToken.getPrincipal();

        User user = new User();
        user.setAccount("account");
        user.setPassword("b0f33830679c502d5988708dcaee958a");
        //set salt
        user.setSalt("HO5Wr5sf");

        if (null != user) {
            SecurityUtils.getSubject().logout();
        }

        //将数据放入info中返回
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
            user,
            user.getPassword(),
            ByteSource.Util.bytes(user.getSalt()),
            getName());
        return info;

    }

}
