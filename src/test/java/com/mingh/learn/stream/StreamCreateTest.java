package com.mingh.learn.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 测试流的创建方式
 */
@Slf4j
public class StreamCreateTest {

    /**
     * create stream by stream static method
     */
    @Test
    public void testCreateStreamWithStreamStaticMethod() {
        // Stream.of()
        Stream<Integer> integerStream = Stream.of(1, 3, 5, 7, 9);
        List<Integer> list = integerStream.collect(Collectors.toList());
        log.info("list is {}", list);

        // Stream.iterate(), i++ 延迟计算并一直输出为 0, ++i 先计算后输出
        List<Integer> list1 = Stream.iterate(0, i -> i++).limit(10).collect(Collectors.toList());
        log.info("list1 is {}", list1);  // [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        List<Integer> list2 = Stream.iterate(0, i -> ++i).limit(10).collect(Collectors.toList());
        log.info("list2 is {}", list2);  // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        // 通过 Stream.generate 方法从外部传入一个提供元素的 Supplier 来构造无限流，然后使用 limit 限制流元素个数
//        List<Double> doubleList = Stream.generate(Math::random).collect(Collectors.toList());  // java.lang.OutOfMemoryError: Java heap space
        List<Double> doubleList = Stream.generate(Math::random).limit(3).collect(Collectors.toList());
        log.info("doubleList is {}", doubleList);

        // 通过 IntStream 或 DoubleStream 构造基本类型的流
        IntStream.range(1, 5).forEach(i -> log.info(String.valueOf(i)));
        IntStream.rangeClosed(1, 5).forEach(i -> log.info(String.valueOf(i)));
        IntStream.rangeClosed(1, 5).mapToObj(String::valueOf).forEach(log::info);
    }

    /**
     * 通过 java.util.Collection.stream() 来创建流
     */
    @Test
    public void testCreateStreamWithArray() {
        int[] array = {1, 3, 5, 7, 9};
        IntStream intStream = Arrays.stream(array);
        intStream.mapToObj(String::valueOf).forEach(log::info);
    }

    /**
     * 通过 java.util.Collection.stream() 来创建流
     */
    @Test
    public void testCreateStreamWithCollection() {
        List<String> list = new ArrayList<String>() {
            {
                add("a");
                add("b");
                add("c");
                add("d");
                add("e");
            }
        };
        // 创建一个顺序流
        Stream<String> stream = list.stream();
        StreamSupport.stream(list.spliterator(), false);
        stream.forEach(log::info);
        // 创建一个并行流
        Stream<String> parallelStream = list.parallelStream();
        parallelStream.forEachOrdered(log::info);
    }
}
