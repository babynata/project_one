import base.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project_one.mybatis.dao.LoginfoMapper;
import project_one.mybatis.dto.Loginfo;

import java.util.List;

public class MybatisMapperTest extends BaseTest{

    @Autowired
    LoginfoMapper loginfoMapper;

    @Test
    public void testMapper() {
        List<Loginfo> list=loginfoMapper.selectAll();
        for (Loginfo loginfo: list
             ) {
            System.out.println(loginfo.toString());
        }
    }
}
