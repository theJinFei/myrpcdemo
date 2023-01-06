package pers.luo.server;

import pers.luo.rpc.Request;
import pers.luo.utils.ReflectionUtils;

public class ServiceInvoke {

    public Object invoke(ServiceInstance serviceInstance, Request request){
        return ReflectionUtils.invoke(serviceInstance.getTarget(), serviceInstance.getMethod(), request.getParameters());
    }
}
