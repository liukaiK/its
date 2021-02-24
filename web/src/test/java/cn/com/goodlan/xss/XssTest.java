package cn.com.goodlan.xss;

import cn.hutool.http.HtmlUtil;
import org.junit.Test;

public class XssTest {

    @Test
    public void test() {
        String html = "<img src='1'/>";
        String content = HtmlUtil.filter(html);
        System.out.println(content);
    }
}
