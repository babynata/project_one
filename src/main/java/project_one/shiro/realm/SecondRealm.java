package project_one.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

public class SecondRealm extends AuthenticatingRealm {

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("[SecondRealm] doGetAuthenticationInfo " + token);

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
//        String credentials = "fc1709d0a95a6be30bc5926fdb7f22f4"; // credentials：密码
        String credentials = null; // credentials：密码
        String realmName = getName();
        AuthenticationInfo info = null;/*new SimpleAuthenticationInfo(principal, credentials, realmName);*/

        if("admin".equals(username)){
            credentials = "ce2f6417c7e1d32c1d81a797ee0b499f87c5de06";
        }else if("user".equals(username)){
            credentials = "073d4c3ae812935f23cb3f2a71943f49e082a718";
        }

        ByteSource credentialsSalt = ByteSource.Util.bytes(username);//这里的参数要给个唯一的;

        info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);

        return info;
    }

    public static void main(String[] args) {
        String hashAlgorithmName = "SHA1";
        String credentials = "123";
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes("Janice");
        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
//        Object obj = new SimpleHash(hashAlgorithmName, credentials, null, hashIterations);
        System.out.println(obj);
    }
}
