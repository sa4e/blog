package cn.sa4e.blog.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.sa4e.blog.model.Blog;
import cn.sa4e.blog.model.Category;
import cn.sa4e.blog.model.Tag;
import cn.sa4e.blog.model.User;
import cn.sa4e.blog.service.IBlogService;
import cn.sa4e.blog.service.ICategoryService;
import cn.sa4e.blog.service.ITagService;
import cn.sa4e.blog.service.IUserService;

/**
* Blog控制器
* @author Sa4e e-mail:hasaigive@gmial.com
* @date 2017年8月24日
*/
@Controller
public class BlogController {
	
	@Autowired
	private IBlogService blogService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private ITagService tagService;
	
	/**
	 * 发布博客
	 * @param blog
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/root/publish")
	public String publishBlog(Blog blog,@RequestParam("tagsGroup") String tagsGroup
							 ,BindingResult bindingResult) throws Exception{
		//TODO 发布博客完善,异常处理
		
		//关联用户
		User user = userService.findOne(blog.getUser().getUid());
		blog.setUser(user);
		
		//关联分类
		Category category = categoryService.findOne(blog.getCategory().getId());
		blog.setCategory(category);
		
		//关联标签
		String[] splitTagName = tagsGroup.split(",");
		Set<Tag> tagSet = new HashSet<>();
		for (String tagName : splitTagName) {
			Tag tag = tagService.findOne(tagName);
			tagSet.add(tag);
		}
		blog.setTags(tagSet);
		
		blogService.insert(blog);
		return "root/index";
	}
	
	
	@GetMapping("/blogs")
	public ModelAndView getBlogList() throws Exception{
		List<Blog> blogList = blogService.findAll();
		return new ModelAndView("index", "blogList", blogList);
	}
	
	@GetMapping("/content/{id}")
	public ModelAndView getContent(@PathVariable("id") Long id) throws Exception{
		Blog content = blogService.findById(id);
		return new ModelAndView("content", "blog", content);
	}
	
}







