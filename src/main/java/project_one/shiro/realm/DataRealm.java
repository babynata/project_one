package project_one.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import project_one.mybatis.dao.LoginfoMapper;
import project_one.mybatis.dto.Loginfo;
import project_one.util.AESUtil;

public class DataRealm extends AuthorizingRealm {

    @Autowired
    private LoginfoMapper loginfoMapper;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken to = (UsernamePasswordToken) token;

        String username = to.getUsername();

        Loginfo loginfo = loginfoMapper.selectOneInfo(username);

        if (null == loginfo) {
            throw new UnknownAccountException("the account doesn't exists.");
        }

        String password = loginfo.getPassword();

        ByteSource pwSalt = ByteSource.Util.bytes(username);

        String realmName = getName();

        AuthenticationInfo info = new SimpleAuthenticationInfo(username, password, pwSalt, realmName);

        return info;
    }
}
