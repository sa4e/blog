package cn.sa4e.blog.result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.sa4e.blog.enums.ErrorCode;

/**
 * RestResultGenerator
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月14日 上午10:39:54
 */
public class RestResultGenerator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestResultGenerator.class);
	
	/**
	 * normal
	 * @param success
	 * @param data
	 * @param message
	 * @return
	 */
	public static <T> RestResult<T> genResult(boolean success, T data, String message) {
        RestResult<T> result = RestResult.newInstance();
        result.setResult(success);
        result.setData(data);
        result.setMessage(message);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("generate rest result:{}", result);
        }
        return result;
    }
	
	/**
	 * success
	 * @param data
	 * @return
	 */
	public static <T> RestResult<T> genSuccessResult(T data,String message) {
        return genResult(true, data, message);
    }
	 
    /**
     * success no data
     * @return
     */
    public static <T> RestResult <T> genSuccessResult(String message) {
        return genSuccessResult(null,message);
    }
    
	
	/**
	 * error message
	 * @param message
	 * @return
	 */
	public static <T> RestResult<T> genErrorResult(String message) {
        return genResult(false, null, message);
    }
	
	/**
	 * error
	 * @param error
	 * @return
	 */
    public static <T> RestResult<T> genErrorResult(ErrorCode error) {
        return genErrorResult(error.getMessage());
    }
	
}
