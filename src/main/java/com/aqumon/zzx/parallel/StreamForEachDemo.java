package com.aqumon.zzx.parallel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @ClassName StreamForEachDemo
 * @Description
 * @Author zhangzx
 * @Date 2020/12/5 14:12
 * Version 1.0
 **/
public class StreamForEachDemo {

    public static void main(String[] args) {
        List<String> title = Arrays.asList("Wmyskxz", "Is", "Learning", "Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
        s.forEach(System.out::println);
    }
}
