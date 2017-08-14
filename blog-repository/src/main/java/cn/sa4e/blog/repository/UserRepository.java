package cn.sa4e.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sa4e.blog.model.User;

/**
 * UserRepository接口
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年8月14日 上午10:26:44
 */
public interface UserRepository extends JpaRepository<User, Long>{

}
