package project_one.druid;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DataSourcesInrterceptor {

    @Pointcut("execution(public * com.*.service..*.selectByPrimaryKey(..))")
    public void dataSourceMaster() {

    }

    @Before("dataSourceMaster()")
    public void before(JoinPoint joinPoint) {
        DataSourcesManager.set(DataSourcesEnum.MASTER);
    }
}
