package com.xiaoniu66.domain;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 爬虫全局请求参数
 * @author Locki
 * @date Mar 31, 2017 3:03:25 PM
 * @version 1.0
 *
 */
public class GlobalParam implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3507712910537503733L;
	@NotEmpty
	private String appKey;
	@NotEmpty
	private String accessToken;
	@NotEmpty
	private String reqId;
	@NotEmpty
	private String callbackUrl;
	@NotEmpty
	private String signature;
	@NotEmpty
	private String nonce;
	@NotEmpty
	private String timestamp;
	
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
