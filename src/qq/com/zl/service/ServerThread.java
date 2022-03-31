package qq.com.zl.service;


import qq.com.zl.comment.Message;
import qq.com.zl.comment.MessageType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * @author zhaolun
 * @version 1.0
 * @item xxx
 */
public class ServerThread extends Thread{
    private Socket socket;
    private String userID;
    LocalDateTime now = LocalDateTime.now();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY年MM月dd日hh:mm:ss E");
    String format = dateTimeFormatter.format(now);
    public ServerThread(Socket socket,String userID){
        this.socket = socket;
        this.userID = userID;
    }

    @Override
    public void run() {
        while (true){
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message message = (Message)objectInputStream.readObject();
                if (message.getMessageType() .equals(MessageType.MESSAGE_GET_ONLINE)){
                    String show = MannerThreadServer.show();
                    Message message2 = new Message();
                    message2.setContent(show);
                    message2.setMessageType(MessageType.MESSAGE_SET_ONLINE);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(message2);

                }else if (message.getMessageType().equals(MessageType.MESSAGE_COMM_MES)){
                    String content = message.getContent();
                    String sender = message.getSender();

                    String getter = message.getGetter();
                    String sendTime = message.getSendTime();
                    message.setContent("\n"+sendTime+"  "+sender+"对你说："+content);

                    Socket socket = MannerThreadServer.getThread(getter).socket;
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(message);
                }else if (message.getMessageType().equals(MessageType.MESSAGE_ALL_MES)){
                    String content = message.getContent();
                    String sender = message.getSender();


                    String sendTime = message.getSendTime();
                    message.setContent(sendTime+"  "+sender+"对大家说："+content);
                    Set<String> key = MannerThreadServer.getKey();

                    for (String s : key) {
                        Socket socket = MannerThreadServer.getThread(s).socket;
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeObject(message);
                    }


                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
