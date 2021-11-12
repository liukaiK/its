package cn.com.goodlan.its.api;

import cn.com.goodlan.its.api.model.TicketResp;
import cn.com.goodlan.its.api.model.UserResp;
import cn.com.goodlan.its.api.util.OkHttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WeLinkController {

    public static final MediaType mediaType = MediaType.parse("application/json");

    @Value("${weLink.ticket.url}")
    private String ticketUrl;

    @Value("${weLink.user.url}")
    private String userUrl;

    @Value("${weLink.user.detail}")
    private String userDetail;

    @Value("${weLink.client.id}")
    private String clientId;

    @Value("${weLink.client.secret}")
    private String clientSecret;

    @GetMapping("/freelogin/{code}")
    public UserResp helloCloudLinkUser(@PathVariable String code) {

        //Step1：获取免登code
        // 免登code可以通过url参数从前端传过来
        // 测试阶段可以通过WeLink扫码复制免登授权码使用https://open-doc.welink.huaweicloud.com/docs/devapi/wecode/jsapi/getAuthCode.html?type=internal


        //Step2:获取access token
        //根据client_id,client_secret和已经获取的免登code等参数获取access_token信息
        TicketResp ticketResp;
        String accessToken = "";

        //获取access token相关参数列表
        String clientIdParamValue = clientId;
        String clientSecretParamValue = clientSecret;
        if (clientId == null || clientId.equals("")) {
            System.out.println("请根据README.md文档配置client_id和client_secret");
            System.exit(1);
        }

        Map<String, String> ticketBodyParams = new HashMap<>();
        ticketBodyParams.put("client_id", clientIdParamValue);
        ticketBodyParams.put("client_secret", clientSecretParamValue);

        //转换相关参数列表为Json格式
        String ticketBody = JSONObject.toJSONString(ticketBodyParams);

        try {
            //发送post请求并获取access token相关信息
            String ticketResult = OkHttpUtil.post(ticketUrl, ticketBody, mediaType);
            ticketResp = JSON.parseObject(ticketResult, TicketResp.class);
            // 请务必根据接口过期时间缓存AccessToken，避免重复获取
            accessToken = ticketResp != null ? ticketResp.getAccess_token() : null;
        } catch (Exception e) {
            e.printStackTrace();
//            return ExceptionUtil.getExceptionInfo(e);
        }

        //Step3:获取免登用户的userId信息
        //通过access token获取免登用户的userId信息
        Map<String, String> userHeaderParams = new HashMap<>();
        UserResp userResp = new UserResp();
        userHeaderParams.put("x-wlk-Authorization", accessToken);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("code", code);
        try {
            //携带access token发送get请求并获取免登用户的userId信息
            String userResult = OkHttpUtil.get(userUrl, userHeaderParams, urlParams);
            System.out.println("userinfo response: " + userResult);
            userResp = JSON.parseObject(userResult, UserResp.class);

            urlParams.put("userId", userResp.getUserId());
            userHeaderParams.put("x-wlk-gray", "0");
            String detail = OkHttpUtil.get(userDetail, userHeaderParams, urlParams);
            userResp = JSON.parseObject(detail, UserResp.class);
        } catch (Exception e) {
            e.printStackTrace();
//            return ExceptionUtil.getExceptionInfo(e);
        }
        return userResp;
    }
}
