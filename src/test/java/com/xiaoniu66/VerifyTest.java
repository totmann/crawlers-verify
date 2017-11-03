package com.xiaoniu66;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.xiaoniu66.domain.GrzxbgReq;
import com.xiaoniu66.domain.SignReq;
import com.xiaoniu66.util.HMACSHA1;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.junit.Test;

/**
 * 爬虫验证
 */
public class VerifyTest {


  static String authUrl = "https://auth-explorer.niudingfeng.com/token";
  static String signature = "a1dc48912c3221a7a4da059ec81de683b1f3477b";
  static String appKey = "crawler-test-system";
  static String appSecret = "4c3e7eeb-a05e-4859-a830-7154a071e6e0";

  static String reqId = "crawler-test-system-"+System.currentTimeMillis();
  static String nonce = "56412";
  static String timestamp = "123456789";
  static String callbackUrl = "http://10.14.1.192:8080/grzxbg/query";

  SignReq getSignReq() {
    SignReq signReq = new SignReq();
    signReq.setAppKey(appKey);
    signReq.setNonce(Integer.parseInt(nonce));
    signReq.setTimestamp(Long.parseLong(timestamp));
    return signReq;
  }

  public String getSignature(SignReq signature) {

    TreeMap<String, String> map = new TreeMap<String, String>();
    map.put("appKey", signature.getAppKey());
    map.put("nonce", signature.getNonce().toString());
    map.put("timestamp", signature.getTimestamp().toString());

    if (signature.getParams() != null) {
      for (Map.Entry<String, String> entry : signature.getParams().entrySet()) {
        map.put(entry.getKey(), entry.getValue());
      }
    }

    String urlStr = HMACSHA1.getURLString(map);
    System.out.println("urlStr: " + urlStr);
    String signatureStr = HMACSHA1.getSignature(urlStr, appSecret);
    if (StringUtils.isBlank(signatureStr)) {
      // 签名出错了
      System.out.println("getSignature[" + signature + "] error: 签名出错");
    }
    System.out.println("getSignature[ " + signature + "] signature: " + signatureStr);
    return signatureStr;
  }

  public String getToken() {
    // POST的URL
    HttpPost httppost = new HttpPost(authUrl);
    httppost.addHeader(HTTP.CONTENT_TYPE, "application/json; charset=utf-8");

    JsonObject obj = new JsonObject();
    obj.addProperty("appKey", appKey);
    obj.addProperty("appSecret", appSecret);
    obj.addProperty("nonce", nonce);
    obj.addProperty("signature", signature);
    obj.addProperty("timestamp", timestamp);
    // 添加参数
    httppost.setEntity(new StringEntity(obj.toString(), "UTF-8"));
    // 设置编码
    HttpResponse response = null;
    try {
      response = new DefaultHttpClient().execute(httppost);
      int status = response.getStatusLine().getStatusCode();
      System.out.println(status);
      org.apache.http.HttpEntity entity = response.getEntity();
      JSONObject json = JSON.parseObject(IOUtils.toString(entity.getContent()));
      String token = json.containsKey("accessToken")?json.getString("accessToken"):null;
      System.out.println(token);
      return token;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Test
  public void testGrzxbgController() throws Exception {

    String strs[] = new String[] { "zyf810810=zyf810810=hr6ipv" };
    for (int i = 0; i < 1; i++) {
      for (String string : strs) {
        execute(string);
      }
    }
  }

  private void execute(String string) throws IOException {
    String tmp[] = string.split("=");
    String username = tmp[0];
    String password = tmp[1];
    String smscode = tmp[2];
    reqId = System.currentTimeMillis() + "";
    SignReq signReq = getSignReq();
    Map<String, String> params = new HashMap<>();
    params.put("callbackUrl", callbackUrl);
    params.put("reqId", reqId);
    params.put("username", username);
    params.put("password", password);
    params.put("smscode", smscode);

    signReq.setParams(params);
    String si = getSignature(signReq);
    System.out.println("=====" + si);


    HttpPost httppost = new HttpPost("http://10.8.37.184:8080/magicrawler-online/grzxbg/query");
//    HttpPost httppost = new HttpPost("http://10.8.47.5:8080/magicrawler-online/grzxbg/query");


    httppost.addHeader(HTTP.CONTENT_TYPE, "application/json; charset=utf-8");
    GrzxbgReq grzxbgReq = new GrzxbgReq();
    grzxbgReq.setAccessToken(getToken());
    grzxbgReq.setAppKey(appKey);
    grzxbgReq.setCallbackUrl(callbackUrl);
    grzxbgReq.setNonce(nonce);
    grzxbgReq.setReqId(reqId);
    grzxbgReq.setSignature(si);
    grzxbgReq.setTimestamp(timestamp);
    grzxbgReq.setUsername(username);
    grzxbgReq.setPassword(password);
    grzxbgReq.setSmscode(smscode);

    String json = JSON.toJSONString(grzxbgReq);
    System.out.println(json);
    StringEntity entity = new StringEntity(json, "UTF-8");
    httppost.setEntity(entity);

    HttpResponse response = new DefaultHttpClient().execute(httppost);
    System.out.println(response.getStatusLine().getStatusCode() + "=="
        + IOUtils.toString(response.getEntity().getContent()));
  }


  @Test
  public void ipTest(){

    System.out.println(getCallbackUrl());
  }

  String getCallbackUrl(){
    try {
      InetAddress ip = InetAddress.getLocalHost();
      return ip.getHostAddress();
    } catch (UnknownHostException e) {
      e.printStackTrace();
      return null;
    }
  }

}
