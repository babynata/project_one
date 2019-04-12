package gitlab.distribution.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZookeeperDemo {

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2182,127.0.0.1:2183,127.0.0.1:2184", 4000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (Event.KeeperState.SyncConnected == event.getState()) {
                    countDownLatch.countDown();
                }
            }
        });
        countDownLatch.await();
        System.out.println(zooKeeper.getState());
        System.out.println("zk-node is synchronized");
//        zooKeeper.close();

        zooKeeper.create("/zk-node-1", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        Stat stat = new Stat();

        Thread.sleep(2000);

        byte[] data=zooKeeper.getData("/zk-node-1", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event.getPath()+"->"+event.getType().toString());
            }
        }, stat);
        System.out.println(new String(data,"utf-8"));

        zooKeeper.setData("/zk-node-1", "1".getBytes(), stat.getVersion());
        data=zooKeeper.getData("/zk-node-1", true, stat);
        System.out.println(new String(data, "utf-8"));

        System.in.read();

    }
}
