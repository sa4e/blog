package cn.sa4e.blog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;

import cn.sa4e.blog.model.Category;
import cn.sa4e.blog.repository.CategoryRepository;
import cn.sa4e.blog.service.ICategoryService;

/**
 * CategoryServiceImpl实现类
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月13日 下午9:35:58
 */
@Service(interfaceName = "cn.sa4e.blog.service.ICategoryService",version = "0.0.1",timeout = 5000)
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	@Transactional
	public void insert(Category category) {
		category.setCreateTime(new Date());
		categoryRepository.save(category);
	}

	@Override
	public List<Category> findAll() {
		List<Category> list = categoryRepository.findAll(new Sort(Direction.DESC, "id"));
		return list;
	}

}
