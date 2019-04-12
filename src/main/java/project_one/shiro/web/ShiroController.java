package project_one.shiro.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project_one.registrator.API_ID;


@Controller
@RequestMapping("shiro")
@API_ID("FA_PROVIDE")
public class ShiroController {

    @RequestMapping("login.do")
    public String login(@RequestParam("username") String username,@RequestParam("password") String password){

        Subject current= SecurityUtils.getSubject();

        if(!current.isAuthenticated()){
            UsernamePasswordToken token=new UsernamePasswordToken(username,password);
            token.setRememberMe(false);
            current.login(token);
        }

        return "redirect:/view/shiro/success.jsp";
    }

}
