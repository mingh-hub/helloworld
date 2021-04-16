package com.mingh.learn.thread;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.stream.Stream;

/**
 * 通过实现 Callable 的方式创建线程
 */
@Slf4j
@Builder
public class ImplCallable implements Callable<Integer> {
    /**
     * 成员变量
     */
    private int i;

    @Override
    public Integer call() throws Exception {
        Stream.iterate(0, Integer::intValue).limit(10).forEach(integer -> {
            i++;
            log.info("当前线程-[{}, value={}]", Thread.currentThread().getName(), i);
        });

        return i;
    }
}
