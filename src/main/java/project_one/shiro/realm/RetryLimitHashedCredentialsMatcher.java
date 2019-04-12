package project_one.shiro.realm;

import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;

import javax.annotation.Resource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type Retry limit hashed credentials matcher.
 *
 * 密码重试次数限制
 * 如在1个小时内密码最多重试5次，如果尝试次数超过5次就锁定1小时，1小时后可再次重试，如果还是重试失败，可以锁定如1天，以此类推，防止密码被暴力破解。
 * 我们通过继承HashedCredentialsMatcher，且使用Ehcache记录重试次数和超时时间
 *
 * 即如果密码输入正确清除cache中的记录；否则cache中的重试次数+1，如果超出5次那么抛出异常表示超出重试次数了。
 *
 * 在构造SimpleAuthenticationInfo后调用doCredentialsMatch验证密码
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Resource(name = "cacheManager")
    private EhCacheManager cacheManager;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        Cache passwordRetryCache=cacheManager.getCache("passwordRetryCache");

        String username = (String)token.getPrincipal();
        //retry count + 1
        Element element = (Element) passwordRetryCache.get(username);
        if(element == null) {
            element = new Element(username , new AtomicInteger(0));
            passwordRetryCache.put(username,element);
        }
        AtomicInteger retryCount = (AtomicInteger)element.getObjectValue();
        if(retryCount.incrementAndGet() > 5) {
            //if retry count > 5 throw
            throw new ExcessiveAttemptsException();
        }

        boolean matches= super.doCredentialsMatch(token, info);

        if(matches) {
            //clear retry count
            passwordRetryCache.remove(username);
        }

        return matches;
    }
}
