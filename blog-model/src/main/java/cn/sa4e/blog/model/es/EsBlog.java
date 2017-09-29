package cn.sa4e.blog.model.es;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import cn.sa4e.blog.model.Blog;
import cn.sa4e.blog.model.Category;
import cn.sa4e.blog.model.Tag;
import cn.sa4e.blog.model.User;

/**
 * Es实体
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月21日 下午10:56:34
 */
@Document(indexName = "blog",type = "EsBlog")
public class EsBlog implements Serializable{

	private static final long serialVersionUID = 551865097890485798L;
	
	/*
	 *一旦添加了@Filed注解，所有的默认值都不再生效。此外，如果添加了@Filed注解，那么type字段必须指定。 
	 *否则报错:No type specified for field
	 */
	
	@Id
	private String id;	//主键
	@Field(index = FieldIndex.not_analyzed,type = FieldType.Long)	//不做全文检索字段  
	private Long blogId; //Blog实体id
	private String title;
	private String imgUrl;
	private String summary;
	private String content;
	private Category category;
	private Set<Tag> tags = new HashSet<>();
	@Field(format = DateFormat.date_time,index = FieldIndex.not_analyzed,type = FieldType.Date)
	private Date createTime;
	@Field(index = FieldIndex.not_analyzed,type = FieldType.Object)
	private User user;
	@Field(index = FieldIndex.not_analyzed,type = FieldType.Integer)
	private Integer display;
	@Field(index = FieldIndex.not_analyzed,type = FieldType.Integer)
	private Integer readSize ;
	
	public EsBlog() {
	}

	public EsBlog(Long blogId, String title, String imgUrl, String summary, String content, Category category, Set<Tag> tags,
			Date createTime, User user, Integer display, Integer readSize) {
		this.blogId = blogId;
		this.title = title;
		this.imgUrl = imgUrl;
		this.summary = summary;
		this.content = content;
		this.category = category;
		this.tags = tags;
		this.createTime = createTime;
		this.user = user;
		this.display = display;
		this.readSize = readSize;
	}

	public EsBlog(Blog blog) {
		this.blogId = blog.getId();
		this.title = blog.getTitle();
		this.imgUrl = blog.getImgUrl();
		this.summary = blog.getSummary();
		this.content = blog.getContent();
		this.category = blog.getCategory();
		this.tags = blog.getTags();
		this.createTime = blog.getCreateTime();
		this.user = blog.getUser();
		this.display = blog.getDisplay();
		this.readSize = blog.getReadSize();
	}
	
	public void update(Blog blog) {
		this.blogId = blog.getId();
		this.title = blog.getTitle();
		this.imgUrl = blog.getImgUrl();
		this.summary = blog.getSummary();
		this.content = blog.getContent();
		this.category = blog.getCategory();
		this.tags = blog.getTags();
		this.createTime = blog.getCreateTime();
		this.user = blog.getUser();
		this.display = blog.getDisplay();
		this.readSize = blog.getReadSize();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public Integer getReadSize() {
		return readSize;
	}

	public void setReadSize(Integer readSize) {
		this.readSize = readSize;
	}
	
}