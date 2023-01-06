package pers.luo.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ReflectionUtils {
    /**
     * 根据class创建对象
     * @param clazz
     * @return
     * @param <T>
     */
    public static  <T> T newInstance(Class<T> clazz){
        try{
            return clazz.newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    /**
     * 根据class获取所有公共方法
     * @param clazz
     * @return
     */
    public static Method[] getPublicMethods(Class clazz){
        // 获取当前类的所有方法，含private
        Method[] declaredMethods = clazz.getDeclaredMethods();
        ArrayList<Method> methodArrayList = new ArrayList<Method>();
        for(int i = 0; i < declaredMethods.length; i++){
            Method method = declaredMethods[i];
            if(Modifier.isPublic(method.getModifiers())){
                methodArrayList.add(method);
            }
        }
        return methodArrayList.toArray(new Method[0]);
    }


    public static Object invoke(Object object, Method method, Object... args){
        try{
            return method.invoke(object, args);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

}
