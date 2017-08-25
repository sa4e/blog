package cn.sa4e.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sa4e.blog.model.Blog;

/**
* BlogRepository接口 
* @author Sa4e e-mail:hasaigive@gmial.com
* @date 2017年8月24日
*/
public interface BlogRepository extends JpaRepository<Blog, Long>{
	
}
