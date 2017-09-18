package cn.sa4e.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sa4e.blog.model.Blog;
import cn.sa4e.blog.repository.BlogRepository;
import cn.sa4e.blog.service.IBlogService;

/**
* BlogServiceImpl实现类
* @author Sa4e e-mail:hasaigive@gmial.com
* @date 2017年8月24日
*/
@Service
public class BlogServiceImpl implements IBlogService {

	@Autowired
	private BlogRepository blogRepository;

	@Override
	@Transactional
	public void insert(Blog blog) {
		blogRepository.save(blog);
	}

	@Override
	public List<Blog> findAll() {
		return blogRepository.findAll();
	}

	@Override
	public Blog findById(Long id) {
		return blogRepository.findOne(id);
	}



	


}
