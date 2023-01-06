package pers.luo.codec;

import junit.framework.TestCase;

public class JSONEncoderTest extends TestCase {


    public void testEncode() {
        Encoder encoder = new JSONEncoder();
        TestBean testBean = new TestBean();
        testBean.setAge(1);
        testBean.setName("luo");
        byte[] encode = encoder.encode(testBean);
        System.out.println(encode.length);
    }
}