package com.xiaoniu66.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.xiaoniu66.domain.FoshanHouseReq;
import com.xiaoniu66.domain.FybzxrReq;
import com.xiaoniu66.domain.GrzxbgReq;
import com.xiaoniu66.domain.GuanzhouHouseReq;
import com.xiaoniu66.domain.RequestVO;
import com.xiaoniu66.domain.SearchEnginesReq;
import com.xiaoniu66.domain.SignReq;
import com.xiaoniu66.domain.SxbzxrReq;
import com.xiaoniu66.util.HMACSHA1;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by owner on 2017/11/2.
 */
@Service
public class VerifyService {


  Logger logger = LoggerFactory.getLogger(getClass());


  static String authUrl = "https://auth-explorer.niudingfeng.com/token";
  static String signature = "a1dc48912c3221a7a4da059ec81de683b1f3477b";
  static String appKey = "crawler-test-system";
  static String appSecret = "4c3e7eeb-a05e-4859-a830-7154a071e6e0";

  static String reqId = "crawler-test-system-"+System.currentTimeMillis();
  static String nonce = "56412";
  static String timestamp = "123456789";
//  static String callbackUrl = "http://10.14.1.192:8080/grzxbg/query";



  /*个人征信报告*/
  public String execute(String string) {
    String tmp[] = string.split("=");
    String username = tmp[0];
    String password = tmp[1];
    String smscode = tmp[2];
    reqId = System.currentTimeMillis() + "";
    SignReq signReq = getSignReq();
    Map<String, String> params = new HashMap<>();
    params.put("callbackUrl", getCallbackUrl("grzxbg"));
    params.put("reqId", reqId);
    params.put("username", username);
    params.put("password", password);
    params.put("smscode", smscode);

    signReq.setParams(params);
    String si = getSignature(signReq);

    HttpPost httppost = new HttpPost("http://10.8.37.184:8080/magicrawler-online/grzxbg/query");

    httppost.addHeader(HTTP.CONTENT_TYPE, "application/json; charset=utf-8");
    GrzxbgReq grzxbgReq = new GrzxbgReq();
    grzxbgReq.setAccessToken(getToken());
    grzxbgReq.setAppKey(appKey);
    grzxbgReq.setCallbackUrl(getCallbackUrl("grzxbg"));
    grzxbgReq.setNonce(nonce);
    grzxbgReq.setReqId(reqId);
    grzxbgReq.setSignature(si);
    grzxbgReq.setTimestamp(timestamp);
    grzxbgReq.setUsername(username);
    grzxbgReq.setPassword(password);
    grzxbgReq.setSmscode(smscode);

    String json = JSON.toJSONString(grzxbgReq);
    logger.info("post json: {}",json);
    StringEntity entity = new StringEntity(json, "UTF-8");
    httppost.setEntity(entity);

    String res = null;
    HttpResponse response = null;
    try {
      response = new DefaultHttpClient().execute(httppost);
      res = IOUtils.toString(response.getEntity().getContent());

    } catch (IOException e) {
      e.printStackTrace();
    }
    return res;
  }

  /*失信被执行人*/
  public String exeSxbzxr(String name){
    String idNum = "";
    String areaName = "";
    reqId = System.currentTimeMillis()+"";
    SignReq signReq = getSignReq();
    Map<String, String> params = new HashMap<>();
    params.put("callbackUrl", getCallbackUrl("sxbzxr"));
    params.put("reqId", reqId);

    params.put("type", "1");
    params.put("name", name);
    signReq.setParams(params);
    String signature = getSignature(signReq);

    String url = "http://10.8.47.5:8080/magicrawler-online/sxbzxr/query";


    HttpPost httppost = new HttpPost(url);

    httppost.addHeader(HTTP.CONTENT_TYPE, "application/json; charset=utf-8");
    SxbzxrReq groupReq = new SxbzxrReq();
    groupReq.setAccessToken(getToken());
    groupReq.setAppKey(appKey);
    groupReq.setCallbackUrl(getCallbackUrl("sxbzxr"));
    groupReq.setSignature(signature);
    groupReq.setTimestamp(timestamp);
    groupReq.setNonce(nonce);
    groupReq.setReqId(reqId);

    groupReq.setName(name);
    groupReq.setType("1");

    StringEntity entity = new StringEntity(JSON.toJSONString(groupReq), "UTF-8");
    httppost.setEntity(entity);

    String res = null;
    HttpResponse response = null;
    try {
      response = new DefaultHttpClient().execute(httppost);
      res = IOUtils.toString(response.getEntity().getContent());

    } catch (IOException e) {
      e.printStackTrace();
    }
    return res;
  }
  /*法院被执行人*/
  public String exeFybzxr(String name) {
    String idNum = "";
    String type = "1";
    reqId = System.currentTimeMillis()+"";
    SignReq signReq = getSignReq();
    Map<String, String> params = new HashMap<>();
    params.put("callbackUrl", getCallbackUrl("fybzxr"));
    params.put("reqId", reqId);
    params.put("name", name);
    params.put("type", type);

    signReq.setParams(params);
    String signature = getSignature(signReq);

    String url = "http://10.8.47.5:8080/magicrawler-online/fybzxr/query";
    HttpPost httppost = new HttpPost(url);
    httppost.addHeader(HTTP.CONTENT_TYPE, "application/json; charset=utf-8");

    FybzxrReq fybzxrReq = new FybzxrReq();
    fybzxrReq.setAccessToken(getToken());
    fybzxrReq.setAppKey(appKey);
    fybzxrReq.setCallbackUrl(getCallbackUrl("fybzxr"));
    fybzxrReq.setIdNum(idNum);
    fybzxrReq.setName(name);
    fybzxrReq.setType(type);

    fybzxrReq.setNonce(nonce);
    fybzxrReq.setReqId(reqId);
    fybzxrReq.setSignature(signature);
    fybzxrReq.setTimestamp(timestamp);
    StringEntity entity = new StringEntity(JSON.toJSONString(fybzxrReq), "UTF-8");
    httppost.setEntity(entity);

    String res = null;
    HttpResponse response = null;
    try {
      response = new DefaultHttpClient().execute(httppost);
      res = IOUtils.toString(response.getEntity().getContent());

    } catch (IOException e) {
      e.printStackTrace();
    }
    return res;
  }

  /*国家企业信用信息网爬虫*/
  public String exeEncredit(String name){

    SignReq signReq = new SignReq();
    signReq.setAppKey(appKey);
    signReq.setNonce(Integer.parseInt(nonce));
    signReq.setTimestamp(Long.parseLong(timestamp));
    Map<String, String> params = new HashMap<>();
    params.put("callbackUrl", getCallbackUrl("encredit"));
    params.put("reqId", reqId);
    params.put("searchKey", name);

    signReq.setParams(params);
    String si = getSignature(signReq);
    String url = "http://10.8.37.242:8080/encredit-crawler/search";
    HttpPost httppost = new HttpPost(url);
    httppost.addHeader(HTTP.CONTENT_TYPE, "application/json; charset=utf-8");
    RequestVO requestVO = new RequestVO();
    requestVO.setAccessToken(getToken());
    requestVO.setAppKey(appKey);
    requestVO.setReqId(reqId);
    requestVO.setSignature(si);
    requestVO.setCallbackUrl(getCallbackUrl("encredit"));
    requestVO.setSearchKey(name);
    requestVO.setNonce(nonce);
    requestVO.setTimestamp(timestamp);
    StringEntity entity = new StringEntity(JSON.toJSONString(requestVO), "UTF-8");
    httppost.setEntity(entity);

    String res = null;
    HttpResponse response = null;
    try {
      response = new DefaultHttpClient().execute(httppost);
      res = IOUtils.toString(response.getEntity().getContent());

    } catch (IOException e) {
      e.printStackTrace();
    }
    return res;

  }


  public String exeGuanzhouHouse(){

    String zigui = "2016登记";// 字轨:2016登记
    String caseNum = "00104301";// 案号:00104301
    String estateNum = "10204426";// 房产证号: 10204426
    String name = "陈兴国";// 权利人姓名 :陈兴国

    SignReq signReq = getSignReq();
    Map<String, String> params = new HashMap<>();
    params.put("callbackUrl", getCallbackUrl("guanzhou"));
    params.put("reqId", reqId);
    params.put("zigui", zigui);
    params.put("caseNum", caseNum);
    params.put("estateNum", estateNum);
    params.put("name", name);

    signReq.setParams(params);
    String signature = getSignature(signReq);

//		String url = "http://10.17.2.121:6000/wuxi/house/query";
    String url = "https://api-crawler-explorer.niudingfeng.com/house/guanzhou/query";
    HttpPost httppost = new HttpPost(url);
    httppost.addHeader(HTTP.CONTENT_TYPE, "application/json; charset=utf-8");
    GuanzhouHouseReq houseReq = new GuanzhouHouseReq();
    houseReq.setAccessToken(getToken());
    houseReq.setAppKey(appKey);
    houseReq.setCallbackUrl(getCallbackUrl("guanzhou"));
    houseReq.setNonce(nonce);
    houseReq.setReqId(reqId);
    houseReq.setSignature(signature);
    houseReq.setTimestamp(timestamp);
    houseReq.setZigui(zigui);
    houseReq.setCaseNum(caseNum);
    houseReq.setName(name);
    houseReq.setEstateNum(estateNum);

    StringEntity entity = new StringEntity(JSON.toJSONString(houseReq), "UTF-8");
    httppost.setEntity(entity);

    String res = null;
    HttpResponse response = null;
    try {
      response = new DefaultHttpClient().execute(httppost);
      res = IOUtils.toString(response.getEntity().getContent());

    } catch (IOException e) {
      e.printStackTrace();
    }
    return res;
  }


  public String exeFoshanHouse(FoshanHouseReq req){

    SignReq signReq = getSignReq();
    Map<String, String> params = new HashMap<>();
    params.put("callbackUrl", getCallbackUrl("foshan"));
    params.put("reqId", reqId);
    params.put("identNum", req.getIdentNum());
    params.put("businessNum", req.getBusinessNum());

    signReq.setParams(params);
    String signature = getSignature(signReq);

		String url = "https://api-crawler-explorer.niudingfeng.com/house/foshan/query";

    HttpPost httppost = new HttpPost(url);
    httppost.addHeader(HTTP.CONTENT_TYPE, "application/json; charset=utf-8");

    req.setAccessToken(getToken());
    req.setAppKey(appKey);
    req.setCallbackUrl(getCallbackUrl("foshan"));
    req.setNonce(nonce);
    req.setReqId(reqId);
    req.setSignature(signature);
    req.setTimestamp(timestamp);

    StringEntity entity = new StringEntity(JSON.toJSONString(req), "UTF-8");
    httppost.setEntity(entity);

    String res = null;
    HttpResponse response = null;
    try {
      response = new DefaultHttpClient().execute(httppost);
      res = IOUtils.toString(response.getEntity().getContent());

    } catch (IOException e) {
      e.printStackTrace();
    }
    return res;
  }


  /*网查爬虫*/
  public String exeSearchEngines(List<String> searchList) {

    final String type = "2";
    SignReq signReq = getSignReq();
    Map<String, String> params = new HashMap<>();
    params.put("callbackUrl", getCallbackUrl("engines"));
    params.put("reqId", reqId);
    params.put("type", type);
    params.put("searchList", searchList.toString());

    signReq.setParams(params);
    String signature = getSignature(signReq);

    String url = "http://10.8.47.5:8082/magicrawler-online-search/engine/query";
    HttpPost httppost = new HttpPost(url);

    httppost.addHeader(HTTP.CONTENT_TYPE, "application/json; charset=utf-8");
    SearchEnginesReq searchEnginesReq = new SearchEnginesReq();
    searchEnginesReq.setAccessToken(getToken());
    searchEnginesReq.setAppKey(appKey);
    searchEnginesReq.setCallbackUrl(getCallbackUrl("engines"));
    searchEnginesReq.setSignature(signature);
    searchEnginesReq.setTimestamp(timestamp);
    searchEnginesReq.setNonce(nonce);
    searchEnginesReq.setReqId(reqId);
    searchEnginesReq.setType(type);

    searchEnginesReq.setSearchList(searchList);

    StringEntity entity = new StringEntity(JSON.toJSONString(searchEnginesReq), "UTF-8");
    httppost.setEntity(entity);
    String res = null;
    HttpResponse response = null;
    try {
      response = new DefaultHttpClient().execute(httppost);
      res = IOUtils.toString(response.getEntity().getContent());

    } catch (IOException e) {
      e.printStackTrace();
    }
    return res;
  }








  String getCallbackUrl(String type){
    try {
      InetAddress ip = InetAddress.getLocalHost();
      return "http://"+ip.getHostAddress()+":8080/"+type+"/query";
    } catch (UnknownHostException e) {
      e.printStackTrace();
      return "http://127.0.0.1:8080/"+type+"/query";
    }
  }


  SignReq getSignReq() {
    SignReq signReq = new SignReq();
    signReq.setAppKey(appKey);
    signReq.setNonce(Integer.parseInt(nonce));
    signReq.setTimestamp(Long.parseLong(timestamp));
    return signReq;
  }

  String getSignature(SignReq signature) {

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
//    System.out.println("urlStr: " + urlStr);
    String signatureStr = HMACSHA1.getSignature(urlStr, appSecret);
    if (StringUtils.isBlank(signatureStr)) {
      // 签名出错了
      System.out.println("getSignature[" + signature + "] error: 签名出错");
    }
//    System.out.println("getSignature[ " + signature + "] signature: " + signatureStr);
    return signatureStr;
  }

  String getToken() {
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
//      System.out.println(status);
      org.apache.http.HttpEntity entity = response.getEntity();
      JSONObject json = JSON.parseObject(IOUtils.toString(entity.getContent()));
      String token = json.containsKey("accessToken")?json.getString("accessToken"):null;
//      System.out.println(token);
      return token;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

}
