package pers.luo.client;

import lombok.Data;
import pers.luo.codec.Decoder;
import pers.luo.codec.Encoder;
import pers.luo.codec.JSONDecoder;
import pers.luo.codec.JSONEncoder;
import pers.luo.rpc.Peer;
import pers.luo.transport.HTTPTransportClient;
import pers.luo.transport.TransportClient;

import java.util.Arrays;
import java.util.List;

@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    private Class<? extends  TransportSelector> selectorClass = RandomTransportSelector.class;

    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(
            new Peer("127.0.0.1", 3000)
    );

}
