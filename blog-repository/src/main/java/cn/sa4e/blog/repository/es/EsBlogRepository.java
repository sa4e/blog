package cn.sa4e.blog.repository.es;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import cn.sa4e.blog.model.es.EsBlog;

/**
 * EsBlogRepository接口
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月21日 下午11:50:54
 */
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, String>{
	
	/**
	 * 模糊查询去重，分页
	 * @param title
	 * @param summary
	 * @param content
	 * @param catName
	 * @param tagName
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrCategoryNameContainingOrTagsNameContaining(
			String title,String summary,String content,String catName,String tagName,Pageable pageable);
	
	
	/**
	 * 根据blog的id 查询EsBlog
	 * @param blogId
	 * @return
	 */
	EsBlog findByBlogId(Long blogId);
	
	/**
	 * 查询所有 display为true的 blog
	 * @return
	 */
	Page<EsBlog> findAllByDisplayEquals(Integer display,Pageable pageable);
	
}








