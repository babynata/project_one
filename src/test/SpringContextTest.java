import base.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextTest extends BaseTest{

    @Autowired
    private ApplicationContext context;

    @Test
    public void testSpringContext(){
        Object shiroFilter = context.getBean("shiroFilter");
        System.out.println(shiroFilter);
    }
}
