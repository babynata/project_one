package gitlab.registrator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class RpcClientTest {

    private static String RPC_PATH = "/ph_yxd_fa/FA_PROVIDE/login.do";

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(RpcConfig.REGISTRATOR_PATH).sessionTimeoutMs(4000).retryPolicy(new ExponentialBackoffRetry(3000, 10)).build();
        curatorFramework.start();

        if (curatorFramework.checkExists().forPath(RpcConfig.SERVICE_ID) != null) {
            if (curatorFramework.checkExists().forPath(RPC_PATH) != null) {
                byte[] addrbys = curatorFramework.getData().forPath(RPC_PATH);
                String addr = new String(addrbys);

                RestTemplate restTemplate = new RestTemplate();
                String result=restTemplate.getForObject(addr, String.class);
                System.out.println(result);
            }
        }
    }
}
