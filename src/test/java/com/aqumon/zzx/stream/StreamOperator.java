package com.aqumon.zzx.stream;

import com.alibaba.fastjson.JSON;
import com.aqumon.zzx.lambda.cart.CartService;
import com.aqumon.zzx.lambda.cart.Sku;
import com.aqumon.zzx.lambda.cart.SkuCategoryEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * 演示流的各种操作
 */
public class StreamOperator {

    List<Sku> list;

    @Before
    public void init( ) {
        list = CartService.getCartSkuList();
    }

    // 中间操作，无状态的操作

    /**
     * filter使用：过滤掉不符合判断的数据
     */
    @Test
    public void filterTest() {
        list.stream()

                // filter
                .filter(sku ->
                        SkuCategoryEnum.BOOKS
                                .equals(sku.getSkuCategory()))

                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));
    }

    /**
     * map使用：将一个元素转换成另一个元素
     */
    @Test
    public void mapTest() {
        list.stream()

                // map
                .map(sku -> sku.getSkuName())

                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));
    }

    /**
     * flatMap（扁平化Map）使用：将一个对象转换成流
     *
     * map和flatMap的区别：
     * 官方解释：
     * map：返回一个流，包含给定函数应用在流中每一个元素后的结果
     * flatMap：返回一个流，包含将此流中的每个元素替换为通过给定函数映射应用于每个元素而生成的映射流的内容
     * 我的解释：
     * map：map方法返回的是一个object，map将流中的当前元素替换为此返回值；
     * flatMap：flatMap方法返回的是一个stream，flatMap将流中的当前元素替换为此返回流拆解的流元素；
     * map是一个一对一的关系，而flatMap是一个一对多的关系
     */
    @Test
    public void flatMapTest() {
        list.stream()

                // flatMap
                .flatMap(sku -> Arrays.stream(
                        sku.getSkuName().split("")))

                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));
    }

    /**
     * peek使用：对流中元素进行遍历操作，与forEach类似，但不会销毁流元素
     * 因为peek操作是一个中间操作，而forEach是一个终端操作，终端操作之后，流就不可用，而peek操作以后还可以后续使用
     * 需要注意：在无状态中间操作的情况下，是交替执行的，而不是先执行完peek，才会执行forEach
     */
    @Test
    public void peek() {
        list.stream()

                // peek
                .peek(sku -> System.out.println(sku.getSkuName()))

                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));
    }

    // 中间操作，有状态的操作

    /**
     * sort使用：对流中元素进行排序，可选则自然排序或指定排序规则。有状态操作
     *
     * 总价按照从小到大排列
     */
    @Test
    public void sortTest() {
        list.stream()

                .peek(sku -> System.out.println(sku.getSkuName()))

                //sort
                .sorted(Comparator.comparing(Sku::getTotalPrice))

                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));
    }

    /**
     * distinct使用：对流元素进行去重。有状态操作
     */
    @Test
    public void distinctTest() {
        list.stream()
                .map(sku -> sku.getSkuCategory())

                // distinct
                .distinct()

                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));


    }

    /**
     * skip使用：跳过前N条记录。有状态操作
     */
    @Test
    public void skipTest() {
        list.stream()

                .sorted(Comparator.comparing(Sku::getTotalPrice))

                // skip
                .skip(3)

                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));
    }

    /**
     * limit使用：截断前N条记录。有状态操作
     *
     * 可以联合skip使用一个假的分页
     */
    @Test
    public void limitTest() {
        list.stream()
                .sorted(Comparator.comparing(Sku::getTotalPrice))

//                .skip(1 * 3)

                // limit
                .limit(3)

                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));
    }

    // 终端操作，短路操作

    /**
     * allMatch使用：终端操作，短路操作。检测是否所有元素都匹配，返回true
     */
    @Test
    public void allMatchTest() {
        boolean match = list.stream()

//                .peek(sku -> System.out.println(sku.getSkuName()))

                // allMatch
                .allMatch(sku -> sku.getTotalPrice() > 100);

        System.out.println(match);
    }

    /**
     * anyMatch使用：任何元素匹配，返回true
     */
    @Test
    public void anyMatchTest() {
        boolean match = list.stream()

                .peek(sku -> System.out.println(sku.getSkuName()))

                // anyMatch
                .anyMatch(sku -> sku.getTotalPrice() > 100);

        System.out.println(match);
    }

    /**
     * noneMatch使用：任何元素都不匹配，返回true
     */
    @Test
    public void noneMatchTest() {
        boolean match = list.stream()

                .peek(sku -> System.out.println(sku.getSkuName()))

                // noneMatch
                .noneMatch(sku -> sku.getTotalPrice() > 10_000);

        System.out.println(match);
    }

    /**
     * 找到第一个
     */
    @Test
    public void findFirstTest() {
        Optional<Sku> optional = list.stream()

                .peek(sku -> System.out.println(sku.getSkuName()))

                // findFirst
                .findFirst();

        System.out.println(
                JSON.toJSONString(optional.get(), true));
    }

    /**
     * 找任意一个
     *
     * findAny和findFirst的区别，是在并行上，findAny少一些，速度快一些
     * 缺点的是，可能随机匹配一个数，返回的数不一致
     */
    @Test
    public void findAnyTest() {
        Optional<Sku> optional = list.stream()

                .peek(sku -> System.out.println(sku.getSkuName()))

                // findAny
                .findAny();

        System.out.println(
                JSON.toJSONString(optional.get(), true));
    }

    // 终端操作，非短路操作

    /**
     * max使用：
     */
    @Test
    public void maxTest() {
        OptionalDouble optionalDouble = list.stream()
                // 所有总价提取出来
                .mapToDouble(Sku::getTotalPrice)
                // 获取总结中最大值
                .max();

        System.out.println(optionalDouble.getAsDouble());
    }

    /**
     * min使用
     */
    @Test
    public void minTest() {
        OptionalDouble optionalDouble = list.stream()
                // 获取总价
                .mapToDouble(Sku::getTotalPrice)

                .min();

        System.out.println(optionalDouble.getAsDouble());
    }

    /**
     * count使用
     */
    @Test
    public void countTest() {
        long count = list.stream()
                .count();

        System.out.println(count);
    }

}
