package cn.sa4e.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sa4e.blog.model.Blog;

/**
 * BlogRepository接口 
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月12日 下午6:59:39
 */
public interface BlogRepository extends JpaRepository<Blog, Long>{
	
}
