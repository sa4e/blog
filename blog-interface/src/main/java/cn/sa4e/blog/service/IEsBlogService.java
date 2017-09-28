package cn.sa4e.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.sa4e.blog.model.es.EsBlog;
/**
 * IEsBlogService 接口
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月24日 下午5:06:03
 */
public interface IEsBlogService {
	
	/**
	 * 删除EsBlog
	 * @param id
	 */
	void removeEsBlog(String id);
	
	/**
	 * 更新EsBlog
	 * @param esBlog
	 * @return
	 */
	EsBlog updateEsBlog(EsBlog esBlog);
	
	/**
	 * 根据blogid 获取EsBlog
	 * @param blogId
	 * @return
	 */
	EsBlog getEsBlogByBlogId(Long blogId);
	
	/**
	 * blog列表,分页
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> listEsBlogs(Integer display,Pageable pageable);
	
	/**
	 * 模糊查询,blog列表,分页
	 * @param keyword
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> listFuzzytEsBlogs(String keyword,Pageable pageable);
	
}
