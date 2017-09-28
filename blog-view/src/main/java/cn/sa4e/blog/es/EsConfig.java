package cn.sa4e.blog.es;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
* @author Sa4e e-mail:hasaigive@gmial.com
* @date 2017年9月15日
*/
@Configuration
@EnableElasticsearchRepositories(basePackages = "cn.sa4e.blog.repository.es.EsBlogRepository")
public class EsConfig {
	
	@Value("${elasticsearch.clustername}")
	private String EsClusterName;
	
	@Value("${elasticsearch.host}")
	private String EsHost;
	
	@Value("${elasticsearch.port}")
	private int EsPort;
	
	@Bean
	public Client client() throws Exception {
		Settings esSettings = Settings.settingsBuilder()
				.put("elastic.name", EsClusterName)
				.build();
		
		return TransportClient.builder()
				.settings(esSettings)
				.build()
				.addTransportAddress(new InetSocketTransportAddress(
						InetAddress.getByName(EsHost), EsPort));
	}
	
	@Bean
	public ElasticsearchOperations elasticsearchTemplate() throws Exception {
		return new ElasticsearchTemplate(client());
	}
	
	 //Embedded Elasticsearch Server 嵌入式?
    /*@Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(nodeBuilder().local(true).node().client());
    }*/


}