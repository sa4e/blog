package cn.sa4e.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

import cn.sa4e.blog.model.User;
import cn.sa4e.blog.repository.UserRepository;
import cn.sa4e.blog.service.IUserService;
/**
 * UserService实现类
 * @author Sa4e
 * @date 2017年8月14日 上午10:21:53
 */
@Service(version = "0.0.1",timeout = 5000)
public class UserServiceImpl implements IUserService{
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void insert(User user) {
		userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		List<User> userList = userRepository.findAll();
		return userList;
	}

}
