package cn.sa4e.blog.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Blog实体
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月12日 上午10:23:14
 */
@Entity
public class Blog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4368769222796651081L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; 
	
	@Column(nullable = false)
	private String imgUrl;		//文章图片
	
	@Column(nullable = false)
	private String title;		//文章标题
	
	@Column(nullable = false)
	@JsonIgnore
	private String summary;		//文章摘要
	
	@Column(nullable = false,columnDefinition = "datetime")	//不能为空
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date createTime;	//文章创建时间
	
	@Lob						//大对象，映射 MySQL 的 Long Text 类型
	@Column(nullable = false)
	@Basic(fetch = FetchType.LAZY)	//懒加载
	@JsonIgnore
	private String content;		//文章内容
	
	@Column(nullable = false,columnDefinition = "tinyint(1)")
	private Integer display = 1;	//是否显示

	@Column(nullable = false,columnDefinition = "tinyint(1)")
	private Integer sticky = 0;		//是否置顶
	
	@Column(nullable = false)
	private Integer readSize = 1;	//访问量、热度
	
	/*
	 * 可选属性optional=false,表示category不能为空
	 * cascade: 级联操作
	 */
	//映射多对一关系
	@ManyToOne(optional = false)
	@JoinColumn(name = "cid")	//设置在blog表中的关联字段(外键)
	private Category category;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "uid")
	@JsonIgnore
	private User user;
	
	/*
	 * 多对多关联关系需要建立一个中间表，所以要用@JoinTable注解来设置中间表的映射关系。
	 * @JoinTable:
	 * 	1.name: 中间表的表名
	 *  2.joinColumns: 配置的是关系维护方主键对应的中间表字段
	 *  	(1)name: 中间表中的列名
	 *  	(2)referencedColumnName:　指定主键列的列名
	 *  3.inverseJoinColumns 配置的是关系被维护一方主键对应的中间表字段(属性同上)
	 */
	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "blog_tag",
			   joinColumns= {@JoinColumn(name = "blogId",referencedColumnName = "id")},
			   inverseJoinColumns= {@JoinColumn(name = "tagId",referencedColumnName = "name")})
	@JsonIgnore
	private Set<Tag> tags = new HashSet<>();
	
	public Blog() {			//jpa规范
		
	}

	public Blog(Long id, String imgUrl, String title, String summary, Date createTime, String content, Integer display, Integer sticky,
			Integer readSize, Category category, User user, Set<Tag> tags) {
		this.id = id;
		this.imgUrl = imgUrl;
		this.title = title;
		this.summary = summary;
		this.createTime = createTime;
		this.content = content;
		this.display = display;
		this.sticky = sticky;
		this.readSize = readSize;
		this.category = category;
		this.user = user;
		this.tags = tags;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getReadSize() {
		return readSize;
	}

	public void setReadSize(Integer readSize) {
		this.readSize = readSize;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public Integer getSticky() {
		return sticky;
	}

	public void setSticky(Integer sticky) {
		this.sticky = sticky;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
}