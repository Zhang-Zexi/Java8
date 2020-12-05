package com.aqumon.zzx.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName StreamParallelDemo
 * @Description 比较stream单线程和多线程的性能差异
 * @Author zhangzx
 * @Date 2020/12/5 13:20
 * Version 1.0
 **/
public class StreamParallelDemo {

    /** 总数 */
    private static int total = 100_000_000;

    public static void main(String[] args) {
        System.out.println(String.format("本计算机的核数：%d", Runtime.getRuntime().availableProcessors()));

        // 产生1000w个随机数(1 ~ 100)，组成列表
        Random random = new Random();
        List<Integer> list = new ArrayList<>(total);

        for (int i = 0; i < total; i++) {
            list.add(random.nextInt(100));
        }

        long prevTime = getCurrentTime();
        list.stream().reduce((a, b) -> a + b).ifPresent(System.out::println);
        System.out.println(String.format("单线程计算耗时：%d", getCurrentTime() - prevTime));

        prevTime = getCurrentTime();
        // 只需要加上 .parallel() 就行了
        list.stream().parallel().reduce((a, b) -> a + b).ifPresent(System.out::println);
        System.out.println(String.format("多线程计算耗时：%d", getCurrentTime() - prevTime));

    }

    private static long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
