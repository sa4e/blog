package cn.sa4e.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;

import cn.sa4e.blog.model.Tag;
import cn.sa4e.blog.repository.TagRepository;
import cn.sa4e.blog.service.ITagService;

/**
 * TagServiceImpl实现类
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月14日 下午6:05:43
 */
@Service(interfaceName = "cn.sa4e.blog.service.ITagService",version = "0.0.1",timeout = 5000)
public class TagServiceImpl implements ITagService {
	
	@Autowired
	private TagRepository tagRepository;
	
	@Override
	@Transactional
	public void insert(Tag tag) {
		int random = new Random().nextInt(9);
		tag.setId(random);
		tag.setCreateTime(new Date());
		tagRepository.save(tag);
	}

	@Override
	public Tag findByName(String name) {
		Tag tag = tagRepository.findOne(name);
		return tag;
	}

	@Override
	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

	@Override
	@Transactional
	public void deleteByName(String name) {
		tagRepository.delete(name);
	}
	

}
