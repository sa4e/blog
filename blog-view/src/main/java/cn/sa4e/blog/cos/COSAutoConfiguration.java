package cn.sa4e.blog.cos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.sign.Credentials;

@Configuration//相当于xml配置文件
@EnableConfigurationProperties(COSProperties.class)
@ConditionalOnClass(COSAutoClient.class)
public class COSAutoConfiguration {
	
	@Autowired
	private COSProperties cosProperties;
	
	@Bean(name = "cosClient")	//相当于<bean id="cosClient" class="com.qcloud.cos.COSClient">xxx</bean>
	public COSClient COSClient() {
		Credentials cred = new Credentials(cosProperties.getAppId(), cosProperties.getSecretId(), cosProperties.getSecretKey());
		ClientConfig config = new ClientConfig();
		config.setRegion(cosProperties.getRegion());
		return new COSClient(config, cred);
	}
	
	@Bean
	@ConditionalOnMissingBean(COSAutoClient.class)
	public COSAutoClient COSAutoClient(/*这里相当于ref="cosClient"*/@Qualifier("cosClient") COSClient cosClient) {
		COSAutoClient cosAutoClient = new COSAutoClient();
		cosAutoClient.setCosClient(cosClient);
		return cosAutoClient;
	}
	
}
