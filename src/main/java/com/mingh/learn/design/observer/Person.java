package com.mingh.learn.design.observer;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Observable;
import java.util.Observer;

/**
 * @ClassName Person
 * @Author Hai.Ming
 * @Date 2021/3/21 10:24 下午
 * @Description 观察者
 */
@Slf4j
@Data
@Builder
public class Person implements Observer {

    private String name;

    @Override
    public void update(Observable o, Object arg) {
        log.info("Observable: {}", o.getClass());
        log.info("arg: {}", arg.getClass());
        bugHouse();  // 观察动作-买房
    }

    /**
     * @MethodName bugHouse
     * @Author Hai.Ming
     * @Date 2021/3/21 10:41 下午
     * @Description 观察者观察到房价上涨后进行买房
     **/
    private void bugHouse() {
        log.info("房价上涨, {} 要去买房了", this.name);
    }
}
