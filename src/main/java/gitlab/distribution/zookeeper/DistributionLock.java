package gitlab.distribution.zookeeper;

import com.alibaba.druid.util.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;

public class DistributionLock implements Watcher{

    private static String CONNECTSTRING = "127.0.0.1:2184,127.0.0.1:2182,127.0.0.1:2183";
    private String LOCK_PATH = "/locks";
    private String WAIT_LOCK;
    private String CURRENT_LOCK;
    private ZooKeeper zooKeeper;
    private CountDownLatch countDownLatch;

    public DistributionLock() {
        try {
            zooKeeper = new ZooKeeper(CONNECTSTRING, 4000, this);
            Stat stat = zooKeeper.exists(LOCK_PATH, false);
            if (stat == null) {
                zooKeeper.create(LOCK_PATH, "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public void lock() {
        try {
            if (tryLock()) {
                System.out.println(Thread.currentThread() + "->get the lock->"+CURRENT_LOCK);
            }
            waitForLock(WAIT_LOCK);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean tryLock() throws KeeperException, InterruptedException {
        CURRENT_LOCK=zooKeeper.create(LOCK_PATH + "/", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(Thread.currentThread()+"->"+CURRENT_LOCK+"->try to get lock");
        List<String> childen = zooKeeper.getChildren(LOCK_PATH, null);
        SortedSet<String> ss = new TreeSet<>();
        for (String child : childen) {
            ss.add(LOCK_PATH+"/"+child);
        }
        SortedSet<String> lessThanMe = ss.headSet(CURRENT_LOCK);
        if (lessThanMe.isEmpty()) {
            return true;
        } else {
            WAIT_LOCK = lessThanMe.last();
        }
/*        String first = lessThanMe.first();
        if (StringUtils.equals(first, CURRENT_LOCK)) {
            return true;
        } else {
            WAIT_LOCK = lessThanMe.last();
        }*/

        return false;
    }

    public void waitForLock(String prev) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(WAIT_LOCK, true);
        if (stat != null) {
            System.out.println(Thread.currentThread()+"->wait for the lock->"+WAIT_LOCK);
            countDownLatch = new CountDownLatch(1);
            countDownLatch.await();
            System.out.println(Thread.currentThread()+"->get the lock");
        }

    }

    public void unlock() {
        try {
            zooKeeper.delete(CURRENT_LOCK,-1);
            CURRENT_LOCK = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args){
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for(int i=0;i<10;i++) {
            new Thread(() ->{
                try {
                    countDownLatch.await();
                    DistributionLock distributionLock = new DistributionLock();
                    distributionLock.lock();
                    Thread.sleep(5000);
                    distributionLock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"Thread"+i).start();
            countDownLatch.countDown();
        }
    }
}
