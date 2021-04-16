package com.mingh.learn.thread;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

@Slf4j
@Builder
public class ExtendThread extends Thread {
    /**
     * 成员变量
     */
    private int i;

    @Override
    public void run() {
        Stream.iterate(0, Integer::intValue).limit(10).forEach(integer -> {
            i++;
            log.info("当前线程-[{}, value={}]", this.getName(), i);
        });
    }
}
