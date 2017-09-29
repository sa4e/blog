package cn.sa4e.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月28日 下午9:16:54
 */
@Controller
public class BaseErrorController implements ErrorController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseErrorController.class);
	private static final String ERROR_PATH = "/error";
	
	@RequestMapping(ERROR_PATH)
	public String error() {
		LOGGER.info("出错了!进入自定义错误控制器...");
		return "404";
	}
	
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

}










