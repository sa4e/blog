package cn.sa4e.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.sa4e.blog.model.Category;
import cn.sa4e.blog.result.RestResult;
import cn.sa4e.blog.result.RestResultGenerator;
import cn.sa4e.blog.service.ICategoryService;

/**
 * 分类控制器
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月13日 下午10:42:16
 */
@RestController
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	
	/**
	 * 创建分类
	 * @param category
	 * @return
	 */
	@PostMapping("/root/cc")
	public RestResult<Category> createCategory(Category category) {
		//TODO 分类完善
		try {
			categoryService.insert(category);
			return RestResultGenerator.genSuccessResult("添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return RestResultGenerator.genErrorResult("添加失败!");
		}
	}
	
	/**
	 * 获取所有的分类列表
	 * @return
	 */
	@GetMapping("/catall")
	public RestResult<List<Category>> findAllCategory() {
		List<Category> all = categoryService.findAll();
		return RestResultGenerator.genSuccessResult(all, null);
	}
	
}













