package pers.luo.server;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import pers.luo.codec.Decoder;
import pers.luo.codec.Encoder;
import pers.luo.rpc.Request;
import pers.luo.rpc.Response;
import pers.luo.transport.RequestHandler;
import pers.luo.transport.TransportServer;
import pers.luo.utils.ReflectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class RpcServer {
    /**
     * 服务配置信息
     */
    private RpcServerConfig config;

    /**
     * 网络配置
     */
    private TransportServer net;

    private Encoder encoder;

    private Decoder decoder;

    private ServiceManager serviceManager;

    private ServiceInvoke serviceInvoke;

    public RpcServer(){
        this(new RpcServerConfig());
    }

    public RpcServer(RpcServerConfig config){
        this.config = config;
        // net
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);
        // codec
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        // service
        this.serviceInvoke = new ServiceInvoke();
        this.serviceManager = new ServiceManager();

    }

    public <T> void register(Class<T> interfaceClass, T bean){
        serviceManager.register(interfaceClass, bean);
    }

    public void start(){
        this.net.start();
    }

    public void stop(){
        this.net.stop();
    }

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream inputStream, OutputStream outputStream) {
            Response response = new Response();
            try{
                byte[] bytes = IOUtils.readFully(inputStream, inputStream.available());
                Request request = decoder.decode(bytes, Request.class);
                log.info("Get Request : {}", request);
                ServiceInstance sis = serviceManager.lookUp(request);
                Object invoke = serviceInvoke.invoke(sis, request);
                response.setData(invoke);
            }catch (Exception e){
                log.warn(e.getMessage(), e);
                response.setCode(1);
                response.setMessage("RpcServer get Error : " + e.getClass());
            }finally {
                byte[] encode = encoder.encode(response);
                try{
                    outputStream.write(encode);
                    log.info("response client success ! ");
                }
                catch (IOException e){
                    log.warn(e.getMessage(), e);
                }

            }
        }
    };
}
