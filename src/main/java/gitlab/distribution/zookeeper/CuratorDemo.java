package gitlab.distribution.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class CuratorDemo {

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().
                connectString("127.0.0.1:2182,127.0.0.1:2183,127.0.0.1:2184").
                sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry(1000, 3)).
                namespace("curator").
                build();
        curatorFramework.start();
//        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/curator-node-1","0".getBytes());
/*        Stat stat = new Stat();
        byte[] data=curatorFramework.getData().storingStatIn(stat).forPath("/curator-node-1");
        System.out.println(new String(data, "utf-8"));
        curatorFramework.setData().withVersion(stat.getVersion()).forPath("/curator-node-1","1".getBytes());
        data = curatorFramework.getData().storingStatIn(stat).forPath("/curator-node-1");
        System.out.println(new String(data, "utf-8"));
        curatorFramework.close();*/
//        nodeCache(curatorFramework,"/curator-node-1");
//pathChildCache(curatorFramework,"/curator-node-1");
        treeCache(curatorFramework,"/curator-node-1");
        System.in.read();
    }

    public static void treeCache(CuratorFramework curatorFramework, String path) throws Exception {
        TreeCache treeCache = new TreeCache(curatorFramework, path);
        TreeCacheListener treeCacheListener = new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                System.out.println(event.getType() + "->" + event.getData().getPath());
            }
        };
        treeCache.getListenable().addListener(treeCacheListener);
        treeCache.start();
    }

    public static void pathChildCache(CuratorFramework curatorFramework,String path) throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, path, true);
        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println(event.getType()+"->"+event.getData().getPath());
            }
        };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start(PathChildrenCache.StartMode.NORMAL);
    }

    public static void nodeCache(CuratorFramework curatorFramework,String path) throws Exception {
        NodeCache nodeCache = new NodeCache(curatorFramework, path, false);
        NodeCacheListener nodeCacheListener = new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("event actived ->" + nodeCache.getCurrentData().getPath());
            }
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();
    }
}
