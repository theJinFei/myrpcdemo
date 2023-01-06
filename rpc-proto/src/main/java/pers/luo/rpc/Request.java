package pers.luo.rpc;

import lombok.Data;

@Data
public class Request {

    private ServiceDescriptor serviceDescriptor;
    private Object[] parameters;
}
