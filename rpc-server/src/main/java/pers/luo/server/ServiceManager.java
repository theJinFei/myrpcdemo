package pers.luo.server;

import lombok.extern.slf4j.Slf4j;
import pers.luo.rpc.Request;
import pers.luo.rpc.ServiceDescriptor;
import pers.luo.utils.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ServiceManager {

    private Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager(){
        this.services = new ConcurrentHashMap<>();
    }

    /**
     * 服务注册 放到一个Map里
     * @param interfaceClass
     * @param bean
     * @param <T>
     */
    public <T> void register(Class<T> interfaceClass, T bean){
        Method[] publicMethods = ReflectionUtils.getPublicMethods(interfaceClass);
        for(int i = 0; i < publicMethods.length; i++){
            Method method = publicMethods[i];
            ServiceInstance serviceInstance = new ServiceInstance(bean, method);
            ServiceDescriptor from = ServiceDescriptor.from(interfaceClass, method);
            services.put(from, serviceInstance);
            log.info("register service : {} {}", from.getClazz(), from.getMethod());
        }
    }

    public ServiceInstance lookUp(Request request){
        ServiceInstance serviceInstance = services.get(request.getServiceDescriptor());;
        return serviceInstance;
    }
}
