package cn.sa4e.blog.service;

import java.util.List;

import cn.sa4e.blog.model.Category;

/**
 * ICategoryService接口
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月13日 下午9:33:53
 */
public interface ICategoryService {
	
	void insert(Category category);
	Category findOne(Long id);
	List<Category> findAll();
	
}
