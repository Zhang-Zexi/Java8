package com.aqumon.zzx.lambda;

import java.util.function.Consumer;

/**
 * 三种方法引用
 */
public class MethodReference {

    /**
     * (args) -> ClassName.staticMethod(args);
     * ClassName::staticMethod;
     * 如果是一个对象调用它的静态方法时，我们就可以使用这个方法了
     */
    public void test1() {
        Consumer<String> consumer1
                = (String number) -> Integer.parseInt(number);

        Consumer<String> consumer2
                = Integer::parseInt;
    }

    /**
     * (args) -> args.instanceMethod();
     * ClassName::instanceMethod;
     * 如果调用传入参数的实例方法
     */
    public void test2() {
        Consumer<String> consumer1
                = (String str) -> str.length();

        Consumer<String> consumer2
                = String::length;
    }

    /**
     * (args) -> object.instanceMethod(args);
     * object::instanceMethod;
     * 如果外部对象调用它自己的方法
     */
    public void test3() {
        StringBuilder stringBuilder = new StringBuilder();

        Consumer<String> consumer1
                = (String str) -> stringBuilder.append(str);

        Consumer<String> consumer2
                = stringBuilder::append;
    }

}
