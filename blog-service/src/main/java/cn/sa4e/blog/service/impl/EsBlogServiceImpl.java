package cn.sa4e.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import cn.sa4e.blog.model.es.EsBlog;
import cn.sa4e.blog.repository.es.EsBlogRepository;
import cn.sa4e.blog.service.IEsBlogService;

/**
* @author Sa4e e-mail:hasaigive@gmial.com
* @date 2017年9月24日
*/
@Service
public class EsBlogServiceImpl implements IEsBlogService {
	
	@Autowired
	private EsBlogRepository esBlogRepository;
	
	@Override
	public void removeEsBlog(String id) {
		esBlogRepository.delete(id);
	}

	@Override
	public EsBlog updateEsBlog(EsBlog esBlog) {
		return esBlogRepository.save(esBlog);
	}

	@Override
	public EsBlog getEsBlogByBlogId(Long blogId) {
		return esBlogRepository.findByBlogId(blogId);
	}

	@Override
	public Page<EsBlog> listEsBlogs(Integer display,Pageable pageable) {
		return esBlogRepository.findAllByDisplayEquals(display,pageable);
	}
	
	@Override
	public Page<EsBlog> listFuzzytEsBlogs(String keyword, Pageable pageable) {
		Page<EsBlog> pages = null;
		Sort sort = new Sort(Direction.DESC,"createTime");		//按照时间降序
		if(pageable.getSort() == null) {
			pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
		}
		
		pages = esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrCategoryNameContainingOrTagsNameContaining(
				keyword, keyword, keyword, keyword, keyword, pageable);
		
		return pages;
	}
	
}
