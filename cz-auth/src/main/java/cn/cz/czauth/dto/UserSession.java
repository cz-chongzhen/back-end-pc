package cn.cz.czauth.dto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *用ThreadLocal保存session信息
 */
@SuppressWarnings("unchecked")
public class UserSession {

    private static ThreadLocal<Map> userThreadLocal = new ThreadLocal<>();

    public UserSession(){

    }

    public static Map getData(){
        Map map = userThreadLocal.get();
        if(map == null){
            map = new ConcurrentHashMap();
            userThreadLocal.set(map);
        }
        return map;
    }

    public static Object getProperty(String key){
        Map map = getData();
        return map.get(key);
    }

    public static void setProperty(String key,Object value){
        Map map = getData();
        if(value!=null){
            map.put(key,value);
        }
    }

    public static long getUserId(){
        Map map = getData();
        return (long) map.get("userId");
    }

    public static void removeSession(){
        userThreadLocal.remove();
    }

}
