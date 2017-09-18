package cn.sa4e.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sa4e.blog.model.Category;
import cn.sa4e.blog.repository.CategoryRepository;
import cn.sa4e.blog.service.ICategoryService;

/**
 * CategoryServiceImpl实现类
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月13日 下午9:35:58
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	@Transactional
	public void insert(Category category) {
		/*category.setCreateTime(new Date());*/
		categoryRepository.save(category);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll(new Sort(Direction.DESC, "id"));
	}

	@Override
	public Category findOne(Long id) {
		return categoryRepository.findOne(id);
	}

}
