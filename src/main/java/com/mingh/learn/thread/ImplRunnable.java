package com.mingh.learn.thread;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

/**
 * 通过实现 Runnable 接口的方式来创建线程
 */
@Slf4j
@Builder
public class ImplRunnable implements Runnable{
    /**
     * 成员变量
     */
    private int i;

    @Override
    public void run() {
        Stream.iterate(0, Integer::intValue).limit(10).forEach(integer -> {
            i++;
            log.info("当前线程-[{}, value={}]", Thread.currentThread().getName(), i);
        });
    }
}
