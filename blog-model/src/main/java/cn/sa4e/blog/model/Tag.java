package cn.sa4e.blog.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 标签实体
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月12日 下午6:50:49
 */
@Entity
public class Tag implements Serializable{

	private static final long serialVersionUID = -7408336002369140395L;
	
	@Id
	private String name;	//标签名为主键
	
	@Column(nullable = false,columnDefinition = "tinyint(1)")
	private Integer id;		//标签的id值,1-8随机分配,用于样式展示
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date createTime;	//创建时间
	
	/*
	* 使用@ManyToMany映射双向关联关系。作为非映射主体一方，只需要简单的
    * 配置该注解的mappedBy="xxx"即可。xxx是对方实体（映射主体）中集合
	* 属性的名称。表示由对方主体的哪个属性来完成映射关系。
	*/
	@ManyToMany(cascade = CascadeType.REFRESH,mappedBy = "tags")
	private Set<Blog> blogs = new HashSet<>();
	
	protected Tag() {
		
	}
	
	public Tag(Integer id, Date createTime, Set<Blog> blogs) {
		this.id = id;
		this.createTime = createTime;
		this.blogs = blogs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Set<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(Set<Blog> blogs) {
		this.blogs = blogs;
	}
}