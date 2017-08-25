package cn.sa4e.blog.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

import cn.sa4e.blog.model.Blog;
import cn.sa4e.blog.repository.BlogRepository;
import cn.sa4e.blog.service.IBlogService;

/**
* BlogServiceImpl实现类
* @author Sa4e e-mail:hasaigive@gmial.com
* @date 2017年8月24日
*/
@Service(interfaceName = "cn.sa4e.blog.service.IBlogService",version = "0.0.1")
public class BlogServiceImpl implements IBlogService {

	@Autowired
	private BlogRepository blogRepository;
	
	@Override
	@Transactional
	public void publish(Blog blog) {
		blogRepository.save(blog);
	}

	@Override
	public List<Blog> getBlogList() {
		return blogRepository.findAll();
	}
	
	@Override
	public Blog getContent(Long id) {
		return blogRepository.findOne(id);
	}


}
