package com.mingh.learn.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程创建方式测试
 */
@Slf4j
public class ThreadTest {

    /**
     * 通过实现 Callable 的方式创建线程
     * 1. 该方式执行可以有返回值, 并且可以抛异常
     * 2. 执行Callable方式, 需要 FutureTask 实现类的支持, 用于接收运算结果
     */
    @Test
    public void testCreateThreadByImplCallable() throws InterruptedException, ExecutionException {
        // 成员变量不共享, 因为创建了两个实例对象
        FutureTask<Integer> task1 = new FutureTask<>(ImplCallable.builder().build());
        FutureTask<Integer> task2 = new FutureTask<>(ImplCallable.builder().build());
        new Thread(task1).start();
        new Thread(task2).start();
        Thread.sleep(3000);
        Optional.of(task1)
                .filter(FutureTask::isDone)
                .ifPresent(task -> {
                    try {
                        log.info("ThreadTest testCreateThreadByImplCallable execute task1 result is {}", task.get());
                    } catch (InterruptedException | ExecutionException e) {
                        log.error("ThreadTest testCreateThreadByImplCallable execute task1 error", e);
                    }
                });
        Optional.of(task2)
                .filter(FutureTask::isDone)
                .ifPresent(task -> {
                    try {
                        log.info("ThreadTest testCreateThreadByImplCallable execute task2 result is {}", task.get());
                    } catch (InterruptedException | ExecutionException e) {
                        log.error("ThreadTest testCreateThreadByImplCallable execute task2 error", e);
                    }
                });
        log.info("========================================================");
        // 成员变量共享, 并且是线程安全的
        ImplCallable implCallable = ImplCallable.builder().build();
        FutureTask<Integer> task3 = new FutureTask<>(implCallable);
        FutureTask<Integer> task4 = new FutureTask<>(implCallable);
        new Thread(task3).start();
        new Thread(task4).start();
        Thread.sleep(3000);
        Optional.of(task3)
                .filter(FutureTask::isDone)
                .ifPresent(task -> {
                    try {
                        log.info("ThreadTest testCreateThreadByImplCallable execute task3 result is {}", task.get());
                    } catch (InterruptedException | ExecutionException e) {
                        log.error("ThreadTest testCreateThreadByImplCallable execute task3 error", e);
                    }
                });
        Optional.of(task4)
                .filter(FutureTask::isDone)
                .ifPresent(task -> {
                    try {
                        log.info("ThreadTest testCreateThreadByImplCallable execute task4 result is {}", task.get());
                    } catch (InterruptedException | ExecutionException e) {
                        log.error("ThreadTest testCreateThreadByImplCallable execute task4 error", e);
                    }
                });
    }

    /**
     * 通过实现 Runnable 接口的方式来创建线程
     * 实现 Runnable 接口的对象实例仅仅作为 Thread 创建的入参, 重写的 run() 函数作为线程的执行体; 时机线程对象
     * 依然是 Thread 实例, 传入的如果是同一个对象实例, 就会存在多线程之间共享变量而引起的线程安全问题
     *
     * 优缺点
     * 优点:
     * 1. 线程只是实现了接口, 还可以继承其它类
     * 2. 多线程创建如果使用同一个实例对象, 适用于多线程处理同一资源的情况
     * 缺点:
     * 1. 相较于继承 Thread 类来说, 编码会稍微复杂
     * 2. 如果要访问当前线程, 需要使用 Thread.currentThread() 函数
     */
    @Test
    public void testCreateThreadByImplRunnable() throws InterruptedException {
        ImplRunnable implRunnable = ImplRunnable.builder().build();
        new Thread(implRunnable).start();
        new Thread(implRunnable).start();
        Thread.sleep(3000);
    }

    /**
     * 通过集成 Thread 类来创建线程
     * 1. 成员变量 i 在线程间不会共享
     * 2. 当通过继承Thread类的方式实现多线程时，可以直接使用this获取当前执行的线程
     *
     * 优缺点
     * 优点:
     * 1. 实现简单, 可以通过 this 关键字直接获取当前线程
     * 缺点:
     * 1. java 是单继承机制, 已经继承了 Thread 类就无法再继承其它的类
     * 2. 线程之间是无法共享变量的
     */
    @Test
    public void testCreateThreadByExtendThread() throws InterruptedException {
        ExtendThread.builder().build().start();
        ExtendThread.builder().build().start();
        // 调用 start() 后, 线程处于激活状态, 但 junit 已经执行完了, 不会输出任何结果, 需要将线程休眠一段时间, 控制台才会有日志输出
        Thread.sleep(3000);
    }
}
