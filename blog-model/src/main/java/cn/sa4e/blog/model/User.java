package cn.sa4e.blog.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

/**
 * User实体
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月12日 下午6:41:32
 */
@Entity
public class User implements Serializable{
	
private static final long serialVersionUID = -3822774101700955700L;

	//uuid生成策略
	@Id
	@GenericGenerator(name = "system-uuid",strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String uid;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Blog> blogs = new HashSet<>();
	
	protected User() {
	}

	public User(String username, String password, Set<Blog> blogs) {
		this.username = username;
		this.password = password;
		this.blogs = blogs;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(Set<Blog> blogs) {
		this.blogs = blogs;
	}
	
}
