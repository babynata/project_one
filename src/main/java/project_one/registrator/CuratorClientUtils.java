package project_one.registrator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

@Component
public class CuratorClientUtils{

    private static CuratorFramework curatorFramework;

    private static String REGISTRATOR = "127.0.0.1:2184,127.0.0.1:2182,127.0.0.1:2183";

    static {
        curatorFramework = CuratorFrameworkFactory.builder().connectString(REGISTRATOR).sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(3000, 10)).build();
        curatorFramework.start();
    }

    public CuratorFramework getCuratorFramework() {
        return curatorFramework;
    }

}
