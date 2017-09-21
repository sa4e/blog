package cn.sa4e.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.sa4e.blog.model.Blog;
import cn.sa4e.blog.model.Category;
import cn.sa4e.blog.model.Tag;
import cn.sa4e.blog.result.RestResult;
import cn.sa4e.blog.result.RestResultGenerator;
import cn.sa4e.blog.service.IBlogService;
import cn.sa4e.blog.service.ICategoryService;
import cn.sa4e.blog.service.ITagService;

/**
* MainController 主页控制器
* @author Sa4e e-mail:hasaigive@gmial.com
* @date 2017年8月24日
*/
@Controller
public class MainController {
	
	@Autowired
	private IBlogService blogService;
	@Autowired
	private ITagService tagService;
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping("/")
	public String index() {
		return "redirect:/blogs";
	}

	@GetMapping("/blogs")
	public String getBlogs(Model model) {
		List<Blog> blogs = blogService.findAll();
		List<Tag> tags = tagService.findAll();
		model.addAttribute("tags", tags);
		model.addAttribute("blogs", blogs);
		return "index";
	}
	
	@GetMapping("/articles/{id}")
	public ModelAndView getContentById(@PathVariable("id") Long id) {
		Blog articles = blogService.findById(id);
		return new ModelAndView("articles", "blog", articles);
	}
	
	@GetMapping("/categories")
	public ModelAndView getCategories() {
		List<Category> categories = categoryService.findAll();
		return new ModelAndView("categories", "cats", categories);
	}
	
	@SuppressWarnings("static-access")
	@GetMapping("/categories/{id}")
	@ResponseBody
	public RestResult<List<Blog>> getBlogsByCid(@PathVariable("id") Long cid) {
		List<Blog> findByCid = blogService.findByCid(cid);
		return new RestResultGenerator().genSuccessResult(findByCid, null);
	}
	
	@GetMapping("/archives")
	public ModelAndView getBlogsOrderByCreateTime() {
		List<Blog> findAllOrderByCreateTimeAsc = blogService.findAllOrderByCreateTimeAsc();
		return new ModelAndView("/archives", "arcs", findAllOrderByCreateTimeAsc);
	}
	
	@GetMapping("/about")
	public String getAbout() {
		return "about";
	}
	
	@GetMapping("/resume")
	public String getResume() {
		return "resume";
	}
}
















