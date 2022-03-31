package qq.com.zl.comment;

import java.io.Serializable;

/**
 * @author zhaolun
 * @version 1.0
 * @item xxx
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String user_id;
    private String password;
    private int state = 0;


    public User() {
    }

    public User(String user_id, String password) {
        this.user_id = user_id;
        this.password = password;
    }

    public User(String user_id, String password, int state) {
        this.user_id = user_id;
        this.password = password;
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
