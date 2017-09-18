package cn.sa4e.blog.enums;
/**
 * 错误码枚举
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月14日 上午10:47:34
 */
public enum ErrorCode {
	
	SERVER_ERROR(-1,"服务器发生未知错误!"),
	ILLEGAL_PARAMS(100,"非法参数!");
	
	private Integer code;
	private String message;
	private ErrorCode(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	public Integer getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
}
