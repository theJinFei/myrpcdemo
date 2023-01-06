package pers.luo.codec;

import com.alibaba.fastjson.JSON;

public class JSONEncoder implements  Encoder{
    @Override
    public byte[] encode(Object object) {
        return JSON.toJSONBytes(object);
    }
}
