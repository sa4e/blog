package cn.sa4e.blog.cos;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = COSProperties.COS_PREFIX)
public class COSProperties {
	
	public static final String COS_PREFIX = "cos";
	
	private long appId;
	private String secretId;
	private String secretKey;
	private String region;
	public long getAppId() {
		return appId;
	}
	public void setAppId(long appId) {
		this.appId = appId;
	}
	public String getSecretId() {
		return secretId;
	}
	public void setSecretId(String secretId) {
		this.secretId = secretId;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
}
