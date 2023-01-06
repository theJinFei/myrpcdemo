package pers.luo.codec;

import junit.framework.TestCase;

public class JSONDecoderTest extends TestCase {

    public void testDecode() {
        Encoder encoder = new JSONEncoder();
        Decoder decoder = new JSONDecoder();
        TestBean testBean = new TestBean();
        testBean.setAge(1);
        testBean.setName("luo");
        byte[] encode = encoder.encode(testBean);
        TestBean testBean1 = decoder.decode(encode, TestBean.class);
        System.out.println(testBean1.toString().equals(testBean.toString()));
    }
}