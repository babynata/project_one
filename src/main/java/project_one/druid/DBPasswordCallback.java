package project_one.druid;

import com.alibaba.druid.util.DruidPasswordCallback;
import com.alibaba.druid.util.StringUtils;
import project_one.util.AESUtil;

import java.util.Properties;

public class DBPasswordCallback extends DruidPasswordCallback {


    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String password = properties.getProperty("password");
        if(!StringUtils.isEmpty(password)){
            try {
                String pwd = AESUtil.AESdecode(password);
                setPassword(pwd.toCharArray());
            } catch (Exception e) {

            }
        }
    }
}
