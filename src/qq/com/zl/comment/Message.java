package qq.com.zl.comment;

import java.io.Serializable;

/**
 * @author zhaolun
 * @version 1.0
 * @item xxx
 */
public class Message implements Serializable {
    private static  final  long serialVersionUID = 1L;
    private String sender;
    private String getter;
    private String content;
    private String sendTime;
    private String messageType;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
