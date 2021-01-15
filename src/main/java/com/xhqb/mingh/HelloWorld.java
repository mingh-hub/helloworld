package com.xhqb.mingh;

import com.xhqb.mingh.utils.SnowflakeIdWorker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class HelloWorld {

    public static void main(String[] args) {
//        System.out.println("=======>");
//
//        System.out.println(SnowflakeIdWorker.build().nextId());
//
//        System.out.println("=======>");
//        Stream<String> stream = Stream.of("How", "do", "you", "do");
//        List<String> list = stream.collect(ArrayList::new, ArrayList::add, (t, u) -> {
//            System.out.println("t:" + t + " u:" + u);
//            t.addAll(u);
//        });
//        System.out.println(list);

        List<Integer> intList = Arrays.asList(1, 3, 2, 4, 9, 8, 6);
//        intList.parallelStream().forEach(System.out::println);  // random
//        intList.parallelStream().forEachOrdered(System.out::println);  // FIFO
        intList.parallelStream().sorted(Integer::compareTo).forEachOrdered(System.out::println);  // ordered

    }
}