package pers.luo.example;

import pers.luo.client.RpcClient;

public class Client {
    public static void main(String[] args) {

        RpcClient client = new RpcClient();
        CalcService proxy = (CalcService) client.getProxy(CalcService.class);
        int add = proxy.add(1, 2);
        int minus = proxy.minus(1, 2);
        System.out.println(add + " || " + minus);
    }
}
