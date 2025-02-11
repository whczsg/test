package com.itheima.bigevent.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 多线程处理工具类
 */
@SuppressWarnings("all")
public class ThreadLocalUtil {
    //提供ThreadLocal对象,
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    //根据键获取值  <T>泛型与T返回值类型必须一致且同名
    public static <T> T get(){
        return (T) THREAD_LOCAL.get(); // 将 Object 类型强制转换为泛型 T 类型
    }
	
    //存储键值对
    public static void set(Object value){
        THREAD_LOCAL.set(value);
    }


    //清除ThreadLocal 防止内存泄漏
    public static void remove(){
        THREAD_LOCAL.remove();
    }
}
