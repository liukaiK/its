package cn.com.goodlan.its.api.util;

import okhttp3.*;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class OkHttpUtil {

    private static  OkHttpClient okHttpClient = new OkHttpClient();

    public static String get(String url, Map<String, String> headers, Map<String, String> urlParams) throws IOException {

        String result = "";
        Request.Builder builder = new Request.Builder();
        if (StringUtils.isEmpty(url)) {
            return result;
        }

        if (headers != null) {
            builder.headers(setHeaders(headers));
        }

        if (urlParams != null) {
            builder.url(setUrlParams(url, urlParams));
        } else {
            builder.url(url);
        }

        Request request = builder.build();
        Response response = okHttpClient.newCall(request).execute();
        result = response.body().string();

        return result;
    }


    public static String post(String url, String json,MediaType mediaType) throws IOException {

        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    private static Headers setHeaders(Map<String, String> headersParams) {

        Headers headers = null;
        Headers.Builder headersbuilder = new Headers.Builder();

        if (headersParams != null) {
            Iterator<String> iterator = headersParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                headersbuilder.add(key, headersParams.get(key));
            }
        }
        headers = headersbuilder.build();

        return headers;
    }

    private static String setUrlParams(String url, Map<String, String> mapParams) {

        String strParams = "";
        if (mapParams != null) {
            Iterator<String> iterator = mapParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                strParams += "&" + key + "=" + mapParams.get(key);
            }
            if (url.endsWith("?")) {
                url += strParams;
            } else {
                url += "?" + strParams;
            }
        }

        return url;
    }

}
