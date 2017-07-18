package name.zhy.ttshop.pojo.vo;

import java.io.Serializable;

/**
 * Created by zhy on 2017/7/3 0003.
 */
public class MessageResult implements Serializable{
    
	private static final long serialVersionUID = 1L;
    //是否成功
    private boolean success;
    //消息
    private String message;
    //状态码
    private int code;
    //结果数据
    private Object data;

    public MessageResult() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
