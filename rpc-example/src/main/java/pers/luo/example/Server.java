package pers.luo.example;

import pers.luo.server.RpcServer;

public class Server {
    public static void main(String[] args) {
        CalcServiceImpl calcService = new CalcServiceImpl();
        RpcServer server = new RpcServer();
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}
