package cn.sa4e.blog.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.CreationTimestamp;

/**
* Blog实体
* @author Sa4e e-mail:hasaigive@gmial.com
* @date 2017年8月24日
*/
@Entity
public class Blog implements Serializable{

	private static final long serialVersionUID = 9203315284550876241L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; 
	
	@Column(nullable = false)
	private String title;	//文章标题
	
	@Column(nullable = false)
	private String summary;	//文章摘要
	
	@Column(nullable = false)//不能为空
	@CreationTimestamp	   //由数据库自动创建时间
	private Timestamp createTime;	//文章创建时间
	
	@Lob	//大对象，映射 MySQL 的 Long Text 类型
	@Column(nullable = false)
	private String content;	//文章内容
	
	protected Blog() {	//jpa规范
	}
	
	public Blog(String title, String summary, String content) {
		this.title = title;
		this.summary = summary;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
















