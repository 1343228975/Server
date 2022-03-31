package qq.com.zl.service;



import qq.com.zl.comment.Message;
import qq.com.zl.comment.MessageType;
import qq.com.zl.comment.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * @author zhaolun
 * @version 1.0
 * @item xxx
 */
public class QqServerService implements Serializable {
    public ServerSocket serverSocket;
    private static HashMap<String, User> users = new HashMap<>();
 static {
     users.put("101",new User("101","123456"));
     users.put("102",new User("102","123456"));
 }

    public boolean checkUser(String user ,String password){
        User user1 = users.get(user);

        if (user1==null){
            return false;
        }


        if (!password.equals(user1.getPassword())){
            return false;
        }
        return true;
    }

    public QqServerService() {
        try {
            serverSocket = new ServerSocket(9999);

            while (true){
                Socket socket = serverSocket.accept();//连接服务器
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());


                User user  = (User)objectInputStream.readObject();


                if (user.getState() == 1){
                    users.put(user.getUser_id(),user);//注册
                }else {
                Message message = new Message();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                //校验账户密码
                if (checkUser(user.getUser_id(),user.getPassword())){

                    //向客户端发送校验结果

                    message.setMessageType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    objectOutputStream.writeObject(message);
                    ServerThread serverThread = new ServerThread(socket, user.getUser_id());
                    serverThread.start();
                    MannerThreadServer.addThread(user.getUser_id(),serverThread);

                }else{

                    message.setMessageType(MessageType.MESSAGE_LOGIN_FAIL);
                    objectOutputStream.writeObject(message);
                    socket.close();
                }}





            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
