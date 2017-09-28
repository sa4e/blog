package cn.sa4e.blog.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.request.UploadFileRequest;

import cn.sa4e.blog.cos.COSAutoClient;
import cn.sa4e.blog.model.Blog;
import cn.sa4e.blog.service.IBlogService;

/**
* Blog控制器
* @author Sa4e e-mail:hasaigive@gmial.com
* @date 2017年8月24日
*/
@Controller
public class BlogController {
	
	@Autowired
	private IBlogService blogService;
	@Autowired
	private COSAutoClient cosAutoClient;
	
	@Value("${cos.bucketName}")
	private String bucketName;
	@Value("${cos.dirName}")
	private String dirName;
	
	/**
	 * 发布博客
	 * @param blog
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/root/publish")
	public String publishBlog(Blog blog,@RequestParam("tagsGroup") String tagsGroup
							 ,BindingResult bindingResult) {
		//TODO 发布博客完善,异常处理
		
		blogService.save(blog,tagsGroup);
		return "root/index";
	}
	
	/**
	 * 富文本编辑器图片上传、封面图片上传
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/root/image")
	@ResponseBody
	public String uploadImg(@RequestParam("file") MultipartFile file) throws IOException {
		//TODO 富文本图片上传接口,异常处理
		COSClient cosClient = cosAutoClient.getCosClient();
		
		String originalFilename = file.getOriginalFilename();
		UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName
				, dirName + UUID.randomUUID().toString() +originalFilename 
				, file.getBytes());
		return cosClient.uploadFile(uploadFileRequest);
		
	}
	
}







