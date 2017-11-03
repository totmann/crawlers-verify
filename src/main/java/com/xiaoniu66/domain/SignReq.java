package com.xiaoniu66.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by owner on 2017/11/2.
 */
public class SignReq implements Serializable {
  private static final long serialVersionUID = 1253410453407178982L;
  private String appKey;
  private Integer nonce;
  private Long timestamp;
  private Map<String, String> params;

  public SignReq() {
  }

  public String getAppKey() {
    return this.appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

  public Integer getNonce() {
    return this.nonce;
  }

  public void setNonce(Integer nonce) {
    this.nonce = nonce;
  }

  public Long getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public Map<String, String> getParams() {
    return this.params;
  }

  public void setParams(Map<String, String> params) {
    this.params = params;
  }
}
