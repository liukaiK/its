package cn.com.goodlan.its.api;

import cn.com.goodlan.its.core.pojo.MessageParam;
import cn.com.goodlan.its.api.model.TicketResp;
import cn.com.goodlan.its.api.model.UserResp;
import cn.com.goodlan.its.api.util.OkHttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WeLinkController {

    public static final MediaType mediaType = MediaType.parse("application/json");
    public static final MediaType mediaTypeForm = MediaType.parse("application/x-www-form-urlencoded");

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

    @Value("${weLink.message.grantType}")
    private String grantType;

    @Value("${weLink.message.url}")
    private String messageUrl;

    @Value("${weLink.message.access_token_url}")
    private String tokenUrl;

    @Value("${weLink.message.appId}")
    private String appId;

    @Value("${weLink.message.appKey}")
    private String appKey;

    @Value("${weLink.welink_url}")
    private String welinkUrl;

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

    @PostMapping("/message")
    public void sendMessage(@RequestBody MessageParam messageParam) throws IOException {
        //获取access token
        //根据client_id,client_secret和已经获取的免登code等参数获取access_token信息
        TicketResp ticketResp;
        String accessToken = "";

        //获取access token相关参数列表
        Map<String, String> ticketBodyParams = new HashMap<>();
        ticketBodyParams.put("client_id", appId);
        ticketBodyParams.put("client_secret", appKey);
        ticketBodyParams.put("grant_type", grantType);

        //转换相关参数列表为Json格式
        String ticketBody = JSONObject.toJSONString(ticketBodyParams);

        try {
            String urlParams = OkHttpUtil.setUrlFormParams(tokenUrl, ticketBodyParams);
            //发送post请求并获取access token相关信息
            String ticketResult = OkHttpUtil.post(urlParams, ticketBody, mediaTypeForm);
            ticketResp = JSON.parseObject(ticketResult, TicketResp.class);
            // 请务必根据接口过期时间缓存AccessToken，避免重复获取
            accessToken = ticketResp != null ? ticketResp.getAccess_token() : null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> message = new HashMap<>();
        message.put("touser", "18445177881");
        message.put("msgtype", "card");
        message.put("title", "您有一条违章信息");
        message.put("content", messageParam.getContent());
        message.put("msgOwner", "违章查询");
        message.put("url", welinkUrl);
        message.put("isForceTips", "1");
        String ticketResult = OkHttpUtil.post(messageUrl + "?access_token=" + accessToken, JSONObject.toJSONString(message), mediaType);
        TicketResp ticket = JSON.parseObject(ticketResult, TicketResp.class);
        System.out.println(ticket.toString());


    }
}
