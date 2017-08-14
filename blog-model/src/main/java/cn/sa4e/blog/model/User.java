package cn.sa4e.blog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User实体
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年8月14日 上午10:24:54
 */
@Entity
public class User implements Serializable{
	
private static final long serialVersionUID = -3822774101700955700L;
	//TODO User实体简单测试,待完善
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long uid;
	private String username;
	private String password;
	private String email;
	
	protected User() {
	}
	
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
