package com.hxh;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class XlassLoader extends ClassLoader {
    public static void main(String[] args) throws Exception {
        // 相关参数
        final String className = "Hello";
        // 创建类加载器
        ClassLoader classLoader = new XlassLoader();
        // 加载相应的类
        Class<?> clazz = classLoader.loadClass(className);
        // 看看里面有些什么方法
        // 创建对象
        //Object instance = clazz.getDeclaredConstructor().newInstance();
        Object instance= clazz.newInstance();
        for (Method m : clazz.getDeclaredMethods()) {
            System.out.println(clazz.getSimpleName() + "." + m.getName());
            // 调用实例方法
            Method method = clazz.getMethod(m.getName());
            method.invoke(instance);
        }


    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 获取输入流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Hello.Xlass");
        try {
            // 读取数据
            int length = inputStream.available();
            byte[] byteArray = new byte[length];
            inputStream.read(byteArray);
            // 转换
            byte[] classBytes = decode(byteArray);
            // 通知底层定义这个类
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 解码
    private static byte[] decode(byte[] byteArray) {
        byte[] targetArray = new byte[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            targetArray[i] = (byte) (255 - byteArray[i]);
        }
        return targetArray;
    }

}
