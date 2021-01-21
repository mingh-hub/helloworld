package com.xhqb.mingh.stream;

import com.xhqb.mingh.beans.Address;
import com.xhqb.mingh.beans.User;
import com.xhqb.mingh.common.enums.ResultEnum;
import com.xhqb.mingh.common.exception.BusinessRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.*;
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
        User user = User.builder().build();
        String province = Optional.ofNullable(user)
                .filter(u -> CollectionUtils.isNotEmpty(u.getAddress()) && Objects.nonNull(u.getAddress().get(0)))
                .map(u -> u.getAddress().get(0))
                .map(Address::getProvince)
                .orElseThrow(() -> new BusinessRuntimeException(ResultEnum.FAILURE));
        log.info("testCreateStreamWithStreamStaticMethod province is {}", province);
    }

    /**
     * 通过 java.util.Collection.stream() 来创建流
     */
    @Test
    public void testCreateStreamWithArray() {
        int[] array = {1, 3, 5, 7, 9};
        IntStream intStream = Arrays.stream(array);
        intStream.forEach(i -> log.info(String.valueOf(i)));
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
