package pers.luo.codec;

public interface Decoder {
    <T> T decode(byte[] bytes, Class<T> tClass);
}
