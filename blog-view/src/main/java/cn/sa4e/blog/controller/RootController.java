package cn.sa4e.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.sa4e.blog.model.User;
import cn.sa4e.blog.service.IUserService;

/**
* Root后台管理控制器
* @author Sa4e e-mail:hasaigive@gmial.com
* @date 2017年8月24日
*/
@Controller
@RequestMapping("/root")
@SessionAttributes(names = {"user"})	//names类型数组,可定义多个session的Key
public class RootController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping
	public String showRootLogin() {
		return "root/login"; 
	}
	
	@GetMapping("/{name}")
	public String showRootContent(@PathVariable("name") String name
								  ,ModelMap modelMap) {
		if("index".equals(name)) {
			User user = (User) modelMap.get("user");
			if(user == null) {
				return "redirect:/root";
			}
			return "root/index";
		}
		return "root/" + name;
	}
	
	/**
	 * 登录后台
	 * @param username
	 * @param password
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/login")
	public String login(@RequestParam(name = "username",required = true) String username,
						@RequestParam(name = "password",required = true) String password,
						ModelMap modelMap) throws Exception{
		//TODO 登录完善,异常处理
		User user = userService.login(username, password);
		if(user == null) {
			modelMap.addAttribute("msg", "User name or password error!");
			return "root/login";
		}
		
		modelMap.addAttribute("user", user);
		return "root/index";
	}
	
}



