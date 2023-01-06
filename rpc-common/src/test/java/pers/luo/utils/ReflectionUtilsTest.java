package pers.luo.utils;

import junit.framework.TestCase;

import java.lang.reflect.Method;

public class ReflectionUtilsTest extends TestCase {

    public void testNewInstance() {
        TestClass testClass = ReflectionUtils.newInstance(TestClass.class);
        testClass.b();
    }

    public void testGetPublicMethods() {
        Method[] publicMethods = ReflectionUtils.getPublicMethods(TestClass.class);
        System.err.println(publicMethods.length);
        System.err.println(publicMethods[0].getName());
    }

    public void testInvoke() {
        Method[] publicMethods = ReflectionUtils.getPublicMethods(TestClass.class);
        TestClass testClass = ReflectionUtils.newInstance(TestClass.class);
        Object invoke = ReflectionUtils.invoke(testClass, publicMethods[0]);
        System.err.println(invoke);

    }
}