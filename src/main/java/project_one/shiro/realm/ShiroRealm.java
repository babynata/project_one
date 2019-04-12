package project_one.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm{

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 1. 从 PrincipalCollection 中获取来获取登录用户的信息
        //由于我们配置了多个Realm 一个返回的是 seconde，一个返回的是认证实体，这两个Realm在配置认证的时候是有顺序地
        /**
         * <property name="realms">
         *    <list>
         *        <ref bean="jdbcRealm"/>
         *        <ref bean="secondRealm"/>
         *    </list>
         *    </property>
         *
         * 当我们在获取Principal的时候也是有顺序的
         */
        Object principal = principals.getPrimaryPrincipal();

        // 2. 利用登录用户的信息来判断当前用户的角色或权限(可能需要查询数据库)
        Set<String> roles = new HashSet<String>();
        roles.add("user"); // 这里无论登录的是user 还是 admin 都存放一个user角色
        if ("admin".equals(principal)) {
            roles.add("admin"); // 如果是admin 授权一个admin角色
        }
        AuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        return info;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("doGetAuthenticationInfo " + token);

        // 1. 把AuthenticationToken 转换为UsernamePasswordToken
        UsernamePasswordToken up = (UsernamePasswordToken) token;
        // 2. 从UsernamePasswordToken 中来获取username
        String username = up.getUsername();
        // 3. 调用数据库的方法，从数据库中查询username对应的用户记录
        System.out.println("从数据库中获取userName ：" + username + " 所对应的用户信息.");
        // 4. 若用户不存在，则可以抛出 UnknownAccoountException 异常
        if ("unknown".equals(username)) {
            throw new UnknownAccountException("用户不存在");
        }
        // 5. 根据用户信息的情况，决定是否需要抛出其他的AuthencationException 异常 假设用户被锁定
        if ("monster".equals(username)) {
            throw new LockedAccountException("用户被锁定");
        }
        // 6. 根据用户的情况，来构建AuthenticationInfo 对象并返回，通常使用的是
        // SimpleAuthenticationInfo
        // 以下信息是从数据库获取的.

        Object principal = username; // principal 认证的实体信息.
        // 可以是username，也可以是数据表对应的用户的实体类对象
        String credentials = "fc1709d0a95a6be30bc5926fdb7f22f4"; // credentials：密码 123456
        String realmName = getName();
        AuthenticationInfo info = new SimpleAuthenticationInfo(principal,
                credentials, realmName);

        return info;
    }
}
