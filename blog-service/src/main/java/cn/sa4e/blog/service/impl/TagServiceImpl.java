package cn.sa4e.blog.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sa4e.blog.model.Tag;
import cn.sa4e.blog.repository.TagRepository;
import cn.sa4e.blog.service.ITagService;

/**
 * TagServiceImpl实现类
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月14日 下午6:05:43
 */
@Service
public class TagServiceImpl implements ITagService {
	
	@Autowired
	private TagRepository tagRepository;
	
	@Override
	@Transactional
	public void insert(Tag tag) {
		tag.setId(new Random().nextInt(8));		//随机生成0-7
	/*	tag.setCreateTime(new Timestamp(new Date().getTime()));*/
		tagRepository.save(tag);
	}

	@Override
	public Tag findOne(String name) {
		return tagRepository.findOne(name);
	}

	@Override
	public List<Tag> listAllTags() {
		return tagRepository.findAll(new Sort(Direction.ASC, "name"));
	}
	
	@Override
	public List<Tag> listTop30Tags(Pageable pageable) {
		return tagRepository.findAll(pageable).getContent();
	}

	@Override
	@Transactional
	public void deleteByName(String name) {
		tagRepository.delete(name);
	}


}
