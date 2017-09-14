package cn.sa4e.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sa4e.blog.model.Category;

/**
 * CategoryRepository接口
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月12日 下午7:02:11
 */
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
