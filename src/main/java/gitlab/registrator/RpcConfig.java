package gitlab.registrator;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Filter;

public class RpcConfig {

    public static String SERVICE_ID = "/ph_yxd_fa";

    public static String REGISTRATOR_PATH = "127.0.0.1:2184,127.0.0.1:2182,127.0.0.1:2183";

    public static String SERVICE_PATH="http://localhost:8080";

    public static void main(String[] args) throws DocumentException {
        String content = "<email><foreach var=\"entry\" items=\"map\">---${entry.key}---<foreach var=\"dto\" items=\"entry\"><foreach var=\"\" items=\"dto.name\">${name}:</foreach>${value}</foreach></foreach></email>";
        Map<String, Object> map = new HashMap<>();


        //outter
        List<ForeachPO> out = new ArrayList<>();
        Map<Integer, Object> outGroup = new HashMap<>();


        Document document = DocumentHelper.parseText(content);
        Element root = document.getRootElement();
        List<Element> els = root.elements("foreach");
        for (Element e : els) {
            ForeachPO foreachPO = new ForeachPO();
            foreachPO.setVar(e.attribute("var").getText());
            foreachPO.setItems(e.attribute("items").getText());
            foreachPO.setContent(e.getText());
            recursives(e,foreachPO);
            out.add(foreachPO);

        }

        for (int i = 0; i < out.size(); i++) {
            Object object = map.get(out.get(i).getItems());
            List<ForeachPO> list = out.get(i).getList();
            if (!CollectionUtils.isEmpty(list)) {
                StringBuilder sb = new StringBuilder();
                for(ForeachPO foreachPO1:list){

                }
            }
        }

        Object object = new Object();
        object.getClass();

    }

    public static void recursives(Element e, ForeachPO foreachPO) {
        List<Element> els = e.elements("foreach");
        if (!CollectionUtils.isEmpty(els)) {
            List<ForeachPO> list = new ArrayList<>();
            for (Element ee : els) {
                ForeachPO foreachPO1 = new ForeachPO();
                foreachPO1.setVar(ee.attribute("var").getText());
                foreachPO1.setItems(ee.attribute("items").getText());
                foreachPO1.setContent(ee.getText());
                recursives(ee,foreachPO1);
                list.add(foreachPO1);
            }
            foreachPO.setList(list);
        }

    }

    static class ForeachPO {

        private String var;
        private String items;
        private String content;
        private Iterator<String> iterator;
        private List<ForeachPO> list;

        public List<ForeachPO> getList() {
            return list;
        }

        public void setList(List<ForeachPO> list) {
            this.list = list;
        }

        public Iterator<String> getIterator() {
            return iterator;
        }

        public void setIterator(Iterator<String> iterator) {
            this.iterator = iterator;
        }

        public String getVar() {
            return var;
        }

        public void setVar(String var) {
            this.var = var;
        }

        public String getItems() {
            return items;
        }

        public void setItems(String items) {
            this.items = items;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }


    static class Person {

        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

}
