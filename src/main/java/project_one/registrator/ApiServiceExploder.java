package project_one.registrator;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

@Component
public class ApiServiceExploder implements BeanPostProcessor,ApplicationContextAware{

    private ApplicationContext applicationContext;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        API_ID apiAnnotation = bean.getClass().getAnnotation(API_ID.class);
        if (apiAnnotation != null) {
            String apiNode = ApiConfig.ROOT_NODE + "/" + apiAnnotation.value();

            CuratorClientUtils curatorClientUtils = applicationContext.getBean(CuratorClientUtils.class);
            if (curatorClientUtils != null) {
                CuratorFramework curatorFramework = curatorClientUtils.getCuratorFramework();
                try {
                    curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(apiNode, "0".getBytes());

                    Method[] methods = bean.getClass().getDeclaredMethods();
                    for (Method method : methods) {
                        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                        if (requestMapping != null) {
                            String[] addrs = requestMapping.value();
                            for (String addr : addrs) {
                                String node = apiNode + "/" + addr;
                                String value = "http://localhost:8080" + "/" + addr;
                                curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(node, value.getBytes());
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }

        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
