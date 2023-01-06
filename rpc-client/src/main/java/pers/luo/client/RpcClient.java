package pers.luo.client;

import pers.luo.codec.Decoder;
import pers.luo.codec.Encoder;
import pers.luo.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

public class RpcClient {

    private RpcClientConfig rpcClientConfig;

    private Encoder encoder;

    private Decoder decoder;

    private TransportSelector selector;

    public  RpcClient(){
        this(new RpcClientConfig());
    }

    public  RpcClient(RpcClientConfig rpcClientConfig){
        this.rpcClientConfig = rpcClientConfig;

        this.encoder = ReflectionUtils.newInstance(rpcClientConfig.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(rpcClientConfig.getDecoderClass());
        this.selector = ReflectionUtils.newInstance(rpcClientConfig.getSelectorClass());
        this.selector.init(this.rpcClientConfig.getServers(), rpcClientConfig.getConnectCount(), rpcClientConfig.getTransportClass());

    }

    public <T> T getProxy(Class<T> clazz){
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{clazz}, new RemoteHandler(clazz,encoder,decoder,selector));
    }
}
