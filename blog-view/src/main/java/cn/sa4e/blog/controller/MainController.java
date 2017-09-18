package cn.sa4e.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* MainController 主页控制器
* @author Sa4e e-mail:hasaigive@gmial.com
* @date 2017年8月24日
*/
@Controller
@RequestMapping("/")
public class MainController {
	
	@GetMapping
	public String index() {
//		return "redirect:/blogs";
		return "index";
	}
	

}
















