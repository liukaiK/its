package cn.com.goodlan.xss;

import cn.com.goodlan.its.core.pojo.entity.primary.Vehicle;
import cn.com.goodlan.its.web.scheduler.AddVipTicket;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class XssTest {

    @Test
    public void test() {
        String html = "<img src='1'/>";
        String content = HtmlUtil.filter(html);
        System.out.println(content);
    }

    @Test
    public void http() throws JsonProcessingException {

        String URL = "https://open.yidianting.xin/openydt/api/v2/addVipTicket?sign=%s";

        String KEY = "jrvzhr";

        String SECRET = "z16lw7wy7z4jxbajid5ecegdres3vdgf";


        String yyyyMMddHHmmss = DateUtil.format(new Date(), "yyyyMMddHHmmss");

        // sign=MD5加密(key+冒号+时间+冒号+密码)，加密后的值应转换为32位小写字符串，如
        // MD5("abc:20170416142030:123456") = c2d404708a925d563fb794929e04f36c
        String sign = DigestUtil.md5Hex(KEY + ":" + yyyyMMddHHmmss + ":" + SECRET);

        Vehicle vehicle = new Vehicle();
        vehicle.setDriverName("刘凯");
        vehicle.setDriverPhone("13384614120");
        vehicle.setLicensePlateNumber("粤AT2Q80");
        String body = new ObjectMapper().writeValueAsString(AddVipTicket.convertFromVehicle(vehicle));
        System.out.println("body = " + body);
        HttpResponse response = HttpRequest.post(String.format(URL, sign))
                .auth(Base64.encode(KEY + ":" + yyyyMMddHHmmss))
                .body(body)
                .timeout(2000) // 超时，毫秒
                .execute().charset(StandardCharsets.UTF_8);


        System.out.println(response.body());


    }
}
