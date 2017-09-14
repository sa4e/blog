package cn.sa4e.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sa4e.blog.model.User;

/**
 * UserRepository接口
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月12日 下午6:59:50
 */
public interface UserRepository extends JpaRepository<User, String>{
	
	//登录后台
	public User findByUsernameAndPassword(String username,String password);
	
}
