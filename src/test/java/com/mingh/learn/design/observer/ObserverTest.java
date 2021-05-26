package com.mingh.learn.design.observer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @ClassName ObserverTest
 * @Author Hai.Ming
 * @Date 2021/3/21 10:37 下午
 * @Description 观察者设计模式
 */
@Slf4j
public class ObserverTest {

    /**
     * @MethodName testObserver
     * @Author Hai.Ming
     * @Date 2021/3/21 11:08 下午
     * @Description 测试观察者设计模式
     *                      1. 问题: 如果不使用 Observer 和 Observable 来实现, 还可以用什么方法实现? 数组
     *                      2. 在实际开发中, 这种模式使用场景较少, 如果一个被观察者出现特别多的观察者, 代码性能不会特别好;
     *                      但是在一些技术框架中会用的特别多, 比如消息中间件(RabbitMQ)的发布-订阅模式
     **/
    @Test
    public void testObserver() {
        // 构建房价观察者
        Person p1 = Person.builder().name("张三").build();
        Person p2 = Person.builder().name("李四").build();
        Person p3 = Person.builder().name("王五").build();
        // 追加观察者
        House house = new House(3000000.0);
        house.addObserver(p1);
        house.addObserver(p2);
        house.addObserver(p3);
        // 房价上涨后触发观察者的动作
        house.setPrice(5000000.0);
    }
}
