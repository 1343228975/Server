package qq.com.zl.service;

import java.util.HashMap;
import java.util.Set;

/**
 * @author zhaolun
 * @version 1.0
 * @item xxx
 */
public class MannerThreadServer {
    private static HashMap<String,ServerThread> thread_map = new HashMap<>();
    public static  void addThread(String userID,ServerThread thread){
        thread_map.put(userID,thread);

    }
    public static ServerThread getThread(String userID){
        return thread_map.get(userID);
    }
    public static String show(){
        String info = "";
        Set<String> keySet = thread_map.keySet();
        for (String o : keySet) {
            info  +=  "\n用户："+o;

        }
       return info;
    }
    public static Set<String> getKey(){
        return thread_map.keySet();
    }



}
