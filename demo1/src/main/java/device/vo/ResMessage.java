package cn.yzw.device.vo;

/**
 * 响应消息
 */
public class ResMessage {

    private Integer code;
    private String message;
    private Object content;

    public ResMessage(Integer code, String message, Object content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public ResMessage(){}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ResMessage{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }
}
