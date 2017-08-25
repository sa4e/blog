package cn.sa4e.blog.service;

import java.util.List;

import cn.sa4e.blog.model.Blog;

/**
* IBlogService接口
* @author Sa4e e-mail:hasaigive@gmial.com
* @date 2017年8月24日
*/
public interface IBlogService {
	
	void publish(Blog blog);
	List<Blog> getBlogList();
	Blog getContent(Long id);
}
