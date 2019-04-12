package gitlab.registrator;

import org.apache.commons.lang3.AnnotationUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.springframework.web.bind.annotation.RequestMapping;
import project_one.shiro.web.ShiroController;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RpcApiPublisher {

    public static CuratorFramework curatorFramework;

    public Map<String, Object> service_instance = new HashMap<>();


    static {
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(RpcConfig.REGISTRATOR_PATH).
                sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry(3000, 10)).build();
        curatorFramework.start();
    }

    public void registrate(String serviceId) throws Exception {
        if (curatorFramework.checkExists().forPath(serviceId) == null) {
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(serviceId, "0".getBytes());
        }
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework,RpcConfig.SERVICE_ID,false);
        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println(event.getType() + "->" + event.getData());
            }
        };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start(PathChildrenCache.StartMode.NORMAL);
    }

    public void bind(Object... instances) {
        for (Object instance : instances) {
            Method[] methods=MethodUtils.getMethodsWithAnnotation(instance.getClass(), RequestMapping.class);
            RequestMapping requestMapping=methods[0].getDeclaredAnnotation(RequestMapping.class);
            String[] apiAddrs = requestMapping.value();
            service_instance.put(apiAddrs[0], instance);
        }
    }

    public void publish() throws Exception {
        Set<String> apiAddr = service_instance.keySet();
        for (String addr : apiAddr) {
            String nodeName = RpcConfig.SERVICE_ID+"/"+addr;
            String nodeValue = RpcConfig.SERVICE_PATH + "/"+addr;
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(nodeName, nodeValue.getBytes());
        }
    }

    public static void main(String[] args){
        RpcApiPublisher rpcApiPublisher = new RpcApiPublisher();
        try {
            rpcApiPublisher.registrate(RpcConfig.SERVICE_ID);
            rpcApiPublisher.bind(new ShiroController());
            rpcApiPublisher.publish();

            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
