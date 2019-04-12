package project_one.freemarker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FreemarkerTest {

    @RequestMapping("toFreemarker.do")
    public ModelAndView toFreemarker(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv=new ModelAndView("text");
        mv.addObject("name","Janice");
        mv.addObject("age","15 months");
        return mv;
    }
}
