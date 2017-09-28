package cn.sa4e.blog.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.sa4e.blog.model.Tag;
import cn.sa4e.blog.result.RestResult;
import cn.sa4e.blog.result.RestResultGenerator;
import cn.sa4e.blog.service.ITagService;

/**
 * 标签控制器
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月14日 下午6:07:32
 */
@RestController
public class TagController {

	@Autowired
	private ITagService tagService;
	
	/**
	 * 创建标签
	 * @param tag
	 * @return
	 */
	@PostMapping("/root/ct")
	public RestResult<Tag> createTag(Tag tag){
		//TODO 完善标签
		try {
			Tag existTag = tagService.findOne(tag.getName());
			if(existTag != null) {	//已存在标签,直接返回
				return RestResultGenerator.genSuccessResult("existed");
			}
			tagService.insert(tag);
			return RestResultGenerator.genSuccessResult("添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return RestResultGenerator.genErrorResult("添加失败!");
		}
	}
	
	/**
	 * 获取所有标签
	 * @return
	 */
	@GetMapping("/tagall")
	public RestResult<String> findAllTag(){
		List<Tag> all = tagService.listAllTags();
		Set<String> tagSet = new HashSet<>();
		for (Tag tag : all) {
			tagSet.add(tag.getName());
		}
		String str = StringUtils.collectionToDelimitedString(tagSet, ",");
		return RestResultGenerator.genSuccessResult(str, null);
	}
	
	/**
	 * 删除标签
	 * @param tag
	 * @return
	 */
	@DeleteMapping("/root/dt")
	public RestResult<Tag> deleteByName(Tag tag){
		try {
			tagService.deleteByName(tag.getName());
			return RestResultGenerator.genSuccessResult("删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return RestResultGenerator.genErrorResult("删除失败!");
		}
	}
}













