package cn.sa4e.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cn.sa4e.blog.model.Blog;

/**
 * BlogRepository接口 
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月12日 下午6:59:39
 */
public interface BlogRepository extends JpaRepository<Blog, Long>{
	
	/**
	 * 更新阅读量
	 * @param readSize
	 * @param id
	 * @return
	 * @deprecated
	 */
	@Transactional
	@Modifying
	@Query("update Blog set read_size = ?1 where id = ?2")	//HQL
	int updateReadSize(Integer readSize,Long id);
	
	/**
	 * 根据分类id查询博客
	 * @param cid
	 * @return
	 */
	@Query("from Blog where cid = ?1")
	List<Blog> findByCid(Long cid);
	
	/**
	 * 根据时间升序查询所有博客
	 * @return
	 */
	List<Blog> findAllByOrderByCreateTimeAsc();
}















