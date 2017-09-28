package cn.sa4e.blog.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sa4e.blog.model.Blog;
import cn.sa4e.blog.model.Category;
import cn.sa4e.blog.model.Tag;
import cn.sa4e.blog.model.User;
import cn.sa4e.blog.model.es.EsBlog;
import cn.sa4e.blog.repository.BlogRepository;
import cn.sa4e.blog.repository.CategoryRepository;
import cn.sa4e.blog.repository.TagRepository;
import cn.sa4e.blog.repository.UserRepository;
import cn.sa4e.blog.service.IBlogService;
import cn.sa4e.blog.service.IEsBlogService;

/**
* BlogServiceImpl实现类
* @author Sa4e e-mail:hasaigive@gmial.com
* @date 2017年8月24日
*/
@Service
public class BlogServiceImpl implements IBlogService {

	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IEsBlogService esBlogService;
	
	@Override
	@Transactional
	public Blog save(Blog blog,String tagsGroup) {
		boolean isNew = (blog.getId() == null);
		EsBlog esBlog = null;
		
		if(isNew) {
			//关联用户
			User user = userRepository.findOne(blog.getUser().getUid());
			blog.setUser(user);
			
			//关联分类
			Category category = categoryRepository.findOne(blog.getCategory().getId());
			blog.setCategory(category);
			
			//关联标签
			String[] splitTagName = tagsGroup.split(",");
			Set<Tag> tagSet = new HashSet<>();
			for (String tagName : splitTagName) {
				Tag tag = tagRepository.findOne(tagName);
				tagSet.add(tag);
			}
			blog.setTags(tagSet);
		}
		
		Blog retunBlog = blogRepository.save(blog);
		
		if(isNew) {
			esBlog = new EsBlog(retunBlog);
		} else {
			esBlog = esBlogService.getEsBlogByBlogId(blog.getId());
			esBlog.update(retunBlog);
		}
		esBlogService.updateEsBlog(esBlog);
		
		return retunBlog;
	}

	@Override
	public List<Blog> findAll() {
		return blogRepository.findAll();
	}
	
	@Override
	public List<Blog> findAllOrderByCreateTimeAsc() {
		return blogRepository.findAllByOrderByCreateTimeAsc();
	}

	@Override
	public Blog findArticlesById(Long id) {
		Blog returnBlog = blogRepository.findOne(id);
		returnBlog.setReadSize(returnBlog.getReadSize() + 1);	//在原有的阅读量上+1
		this.save(returnBlog, null);							//更新
		return returnBlog;
	}

	@Override
	public List<Blog> findByCid(Long cid) {
		return blogRepository.findByCid(cid);
	}

	

}
