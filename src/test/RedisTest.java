import base.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import project_one.shiro.cache.RedisCacheUtil;


import javax.annotation.Resource;

public class RedisTest extends BaseTest{

    @Resource(name = "redisCache")
    private RedisCacheUtil redisCacheUtil;

    private static String key;
    private static String field;
    private static String value;

/*    @Before
    public void setUp(){
        String[] path={"applicationContext-shiro.xml","controller-servlet.xml"};
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext(path);
        context.start();
        redisCacheUtil= (RedisCacheUtil) context.getBean("RedisCacheUtil");
    }*/

    static {
        key="student";
        field="stu_name";
        value="stu_info";
    }

    @Test
    public void testHset(){
        redisCacheUtil.hset(key,field,value);
        System.out.println("put success");
    }

    @Test
    public void testHget(){
        String re=redisCacheUtil.hget(key,field);
        System.out.println(re);
    }

    @Test
    public void testHsize(){
        System.out.println(redisCacheUtil.hsize(key));
    }

}
