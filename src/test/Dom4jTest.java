import base.BaseTest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Dom4jTest extends BaseTest {

    @Test
    public void test() throws DocumentException {
        String content = "<email>" +
                "<foreach hasInner=\"false\">" +
                "3" +
                "</foreach>" +
                "<foreach hasInner=\"true\">" +
                "1" +
                "<foreach hasInner=\"fasle\">2</foreach>" +
                "</foreach>" +
                "</email>";
        StringBuilder newContent = new StringBuilder();
        Document document = DocumentHelper.parseText(content);
        Element root = document.getRootElement();
        //先处理外层串行foreach
        List<Element> es = new ArrayList<>();
        while (root.element("foreach") != null) {
            Element e = root.element("foreach");
            es.add(e);
            root.remove(e);
        }
        if (CollectionUtils.isEmpty(es)) {
            for (Element e : es) {
                if (e.element("foreach") != null) {
                    List<Element> ess = serial(e);

                } else {
                    String r = e.getText();
                    r = "replace" + r;
                    Integer loop = new Integer(r);
                    int i = 0;
                    while (i < loop) {
                        newContent.append(r);
                        ++i;
                    }
                }
            }
        }
    }

    public static List<Element> serial(Element e) {
        List<Element> es = new ArrayList<>();
        while (e.element("foreach") != null) {
            Element ee = e.element("foreach");
            es.add(ee);
            e.remove(ee);
        }
        return es;
    }


}
