package cn.sa4e.blog.result;
/**
 * 返回结果实体
 * @author Sa4e e-mail:hasaigive@gmail.com
 * @date 2017年9月14日 上午10:29:13
 * @param <T>
 */
public class RestResult<T> {
	
	private boolean result;
    private String message;
    private T data;
    
    private RestResult() {}

    public static <T> RestResult<T> newInstance() {
        return new RestResult<>();
    }

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "RestResult{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
	}
	
}
