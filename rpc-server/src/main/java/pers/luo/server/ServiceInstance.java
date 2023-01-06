package pers.luo.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

@Data
@AllArgsConstructor
public class ServiceInstance {

    // 哪个对象暴露出哪个方法
    private Object target;
    private Method method;

}
