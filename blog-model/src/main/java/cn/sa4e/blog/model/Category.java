package cn.sa4e.blog.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * 分类实体
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月12日 下午6:50:40
 */
@Entity
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4607308137144116367L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;			//分类名称
	
	@Column(nullable = false,columnDefinition = "datetime")
	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTime;		//分类创建时间
	
	/*
	 * mappedBy属性相当于inverse=true,拥有mappedBy注解的实体类为关系被维护的一端
	 * mappedBy="category"中的category是"多"的一方中持有"一"的一方的属性名
	 * cascade: 级联操作
	 */
	//映射一对多关系
	@OneToMany(mappedBy = "category",cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Blog> blogs = new HashSet<>();
	
	public Category() {	
	}

	public Category(Long id, String name, Date createTime, Set<Blog> blogs) {
		this.id = id;
		this.name = name;
		this.createTime = createTime;
		this.blogs = blogs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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