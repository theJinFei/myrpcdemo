package pers.luo.client;

import pers.luo.rpc.Peer;
import pers.luo.transport.TransportClient;

import java.util.List;

public interface TransportSelector {

    /**
     * 初始化所有的server端点
     * @param peers 可以连接的server端点信息
     * @param count 每个server端点肿，client与server建立多少个连接
     * @param clazz client实现calss
     */
    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz);

    /**
     * 选择一个transport 和server做交互
     * @return 网络client
     */
    TransportClient select();

    /**
     * 释放用完的client
     * @param client
     */
    void release(TransportClient client);

    void close();
}
