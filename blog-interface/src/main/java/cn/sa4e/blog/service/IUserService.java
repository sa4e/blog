package cn.sa4e.blog.service;

import java.util.List;

import cn.sa4e.blog.model.User;

/**
 * IUserService接口
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年8月14日 上午10:23:51
 */
public interface IUserService {
	
	void insert(User user) throws Exception;
	User login(String username,String password) throws Exception;
	List<User> getAllUser() throws Exception;
}
