package cn.sa4e.blog.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import cn.sa4e.blog.model.Tag;

/**
 * ITagService接口
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月14日 下午6:01:41
 */
public interface ITagService {
	
	void insert(Tag tag);
	Tag findOne(String name);
	List<Tag> listAllTags();
	List<Tag> listTop30Tags(Pageable pageable);
	void deleteByName(String name);
	
}
