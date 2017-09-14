package cn.sa4e.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;

import cn.sa4e.blog.model.User;
import cn.sa4e.blog.repository.UserRepository;
import cn.sa4e.blog.service.IUserService;
import cn.sa4e.blog.utils.MD5Utils;
/**
 * UserService实现类
 * @author Sa4e
 * @date 2017年8月14日 上午10:21:53
 */
/*dubbio的@Service注解不能和@Transactional 同时使用
 *通过修改dubbo/config/annotation/Service.class源码,添加@Inherited注解 
 *并且强制使用cglib动态代理.可解决此问题
 *发布服务格式如下:
 */
@Service(interfaceName = "cn.sa4e.blog.service.IUserService",version = "0.0.1",timeout = 5000)
public class UserServiceImpl implements IUserService{
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public void insert(User user) throws Exception {
		userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() throws Exception {
		List<User> userList = userRepository.findAll();
		return userList;
	}

	@Override
	public User login(String username, String password) throws Exception {
		password = MD5Utils.md5(password);
		User user = userRepository.findByUsernameAndPassword(username, password);
		return user;
	}

}
