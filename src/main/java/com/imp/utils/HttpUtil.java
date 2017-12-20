package com.imp.utils;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;

public class HttpUtil {
    private Gson gson = new GsonBuilder().serializeNulls().create();
    public static String httpPost(String url, Object data) throws IOException {
        String response = Request.Post(url)
                .bodyString(JSON.toJSONString(data), ContentType.APPLICATION_JSON).execute()
                .returnContent().asString();
        return response;
    }
}
