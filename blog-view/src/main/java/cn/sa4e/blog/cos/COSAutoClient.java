package cn.sa4e.blog.cos;

import com.qcloud.cos.COSClient;

public class COSAutoClient {
	
	private COSClient cosClient;

	public COSClient getCosClient() {
		return cosClient;
	}

	public void setCosClient(COSClient cosClient) {
		this.cosClient = cosClient;
	}
	
}
