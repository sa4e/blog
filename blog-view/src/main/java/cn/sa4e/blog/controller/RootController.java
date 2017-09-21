package cn.sa4e.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView login(User user) throws Exception{
		//TODO 登录完善,异常处理
		User u = userService.login(user.getUsername(), user.getPassword());
		if(u == null) {
			return new ModelAndView("root/login", "msg", "User name or password error!");
		}
		
		return new ModelAndView("root/index", "user", u);
	}
	
}



