package cn.sa4e.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.sa4e.blog.model.Blog;
import cn.sa4e.blog.model.Category;
import cn.sa4e.blog.model.Tag;
import cn.sa4e.blog.model.es.EsBlog;
import cn.sa4e.blog.result.RestResult;
import cn.sa4e.blog.result.RestResultGenerator;
import cn.sa4e.blog.service.IBlogService;
import cn.sa4e.blog.service.ICategoryService;
import cn.sa4e.blog.service.IEsBlogService;
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
	@Autowired
	private IEsBlogService esBlogService;
	
	private final static Integer DISPLAY = 1;		//显示blog
	private final static Integer TOP30TAGS = 30;	//前30个tag
	
	@GetMapping("/")
	public String index() {
		return "redirect:/blogs";
	}
	
	/**
	 * 首页数据及分页
	 * @param async
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@GetMapping("/blogs")
	public String getBlogs(
			@RequestParam(value = "async", required = false) boolean async,
			@RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "6") int pageSize,
			Model model) {
		//TODO 添加首页右侧栏若干项等属性
		
		Sort sort = new Sort(Direction.DESC,"createTime");
		Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
		Page<EsBlog> page = esBlogService.listEsBlogs(DISPLAY, pageable);
		List<EsBlog> list = page.getContent();	// 当前所在页面数据列表
		
		model.addAttribute("blogs", list);
		
		//首次加载页面才加载
		if(!async) {
			List<Tag> tags = tagService.listTop30Tags(new PageRequest(0, TOP30TAGS));
			model.addAttribute("tags", tags);
		}
		return (async == true? "index :: #mainContainerRepleace" : "index");
	}
	
	/**
	 * 首页数据总数
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("static-access")
	@GetMapping("/blogs/totalElements")
	@ResponseBody
	public RestResult<Long> getTotalElements(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "6") int pageSize) {
		
		Page<EsBlog> blogs = esBlogService.listEsBlogs(DISPLAY,new PageRequest(pageIndex, pageSize));
		long totalElements = blogs.getTotalElements();
		return new RestResultGenerator().genSuccessResult(totalElements, "get totalElements success!");
	}
	
	/**
	 * 搜索数据及分页
	 * @param async
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@GetMapping("/blogs/q/{keyword}")
	public String getFuzzyBlogs(
			@RequestParam(value = "async", required = false) boolean async,
			@PathVariable(value = "keyword", required = false) String keyword,
			@RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "9") int pageSize,
			Model model) {
		
		//TODO 搜索优化及异常处理
		
		Sort sort = new Sort(Direction.DESC,"createTime");
		Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
		Page<EsBlog> page = esBlogService.listFuzzytEsBlogs(keyword, pageable);
		List<EsBlog> fuzzyBlogs = page.getContent();
		
		model.addAttribute("fuzzyBlogs", fuzzyBlogs);
		
		if(!async) {
			List<Tag> allTags = tagService.listAllTags();
			model.addAttribute("allTags", allTags);
		}
		
		return (async == true? "query :: #mainContainerRepleace" : "query");
	}
	
	/**
	 * 搜索关键字返回数据的总数
	 * @param pageIndex
	 * @param pageSize
	 * @param keyword
	 * @return
	 */
	@SuppressWarnings("static-access")
	@GetMapping("/blogs/{keyword}/totalElements")
	@ResponseBody
	public RestResult<Long> getKeyWordTotalElements(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "9") int pageSize,
			@PathVariable(value = "keyword") String keyword) {
		
		Page<EsBlog> page = esBlogService.listFuzzytEsBlogs(keyword, new PageRequest(pageIndex, pageSize));
		long totalElements = page.getTotalElements();
		
		return new RestResultGenerator().genSuccessResult(totalElements, "get fuzzy totalElements success!");
	}
	
	/**
	 * 根据blogId获取文章
	 * @param id
	 * @return
	 */
	@GetMapping("/articles/{id}")
	public ModelAndView getArticlesById(@PathVariable("id") Long id) {
		Blog articles = blogService.findArticlesById(id);
		return new ModelAndView("articles", "blog", articles);
	}
	
	/**
	 * 分类page
	 * @return
	 */
	@GetMapping("/categories")
	public ModelAndView getCategories() {
		List<Category> categories = categoryService.findAll();
		return new ModelAndView("categories", "cats", categories);
	}
	
	/**
	 * 根据分类id,获取该分类所有文章
	 * @param cid
	 * @return
	 */
	@SuppressWarnings("static-access")
	@GetMapping("/categories/{id}")
	@ResponseBody
	public RestResult<List<Blog>> getBlogsByCid(@PathVariable("id") Long cid) {
		List<Blog> findByCid = blogService.findByCid(cid);
		return new RestResultGenerator().genSuccessResult(findByCid, null);
	}
	
	/**
	 * 档案page
	 * @return
	 */
	@GetMapping("/archives")
	public ModelAndView getBlogsOrderByCreateTime() {
		List<Blog> findAllOrderByCreateTimeAsc = blogService.findAllOrderByCreateTimeAsc();
		return new ModelAndView("archives", "arcs", findAllOrderByCreateTimeAsc);
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
















