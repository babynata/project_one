package betterdesign.shield;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

public class IDRule implements ShieldRules{


    @Override
    public ShieldRulesPO getShieldRulesPo() {
        System.out.println("using ID type shield rules to handle...");
        return null;
    }
}
