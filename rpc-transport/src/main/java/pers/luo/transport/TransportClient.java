package pers.luo.transport;

import pers.luo.rpc.Peer;

import java.io.InputStream;

public interface TransportClient {

    void connect(Peer peer);

    InputStream write(InputStream inputStream);

    void close();
}
