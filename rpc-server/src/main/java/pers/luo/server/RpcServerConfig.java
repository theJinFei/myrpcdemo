package pers.luo.server;

import lombok.Data;
import pers.luo.codec.Decoder;
import pers.luo.codec.Encoder;
import pers.luo.codec.JSONDecoder;
import pers.luo.codec.JSONEncoder;
import pers.luo.transport.HTTPTransportServer;
import pers.luo.transport.TransportServer;

@Data
public class RpcServerConfig {

    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    private int port = 3000;
}
