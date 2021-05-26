package com.mingh.learn.design.observer;

import lombok.extern.slf4j.Slf4j;

import java.util.Observable;

/**
 * @ClassName House
 * @Author Hai.Ming
 * @Date 2021/3/21 10:16 下午
 * @Description 房子, 被观察者
 */
@Slf4j
public class House extends Observable {
    private static final long serialVersionUID = 5336796761202350310L;

    private Double price;

    public House() {
    }

    public House(double price) {
        this.price = price;
    }

    public void setPrice(double price) {
        // 房价上涨, 促进观察者购买
        if (price > this.price) {
            log.info("房价上涨了!!!");
            super.setChanged();  // 房价改变了
            super.notifyObservers(price);  // 通知观察者,
        }
        this.price = price;
    }
}
