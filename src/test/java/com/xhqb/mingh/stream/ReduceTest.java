package com.xhqb.mingh.stream;

import com.google.common.collect.Lists;
import com.xhqb.mingh.beans.Address;
import com.xhqb.mingh.beans.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

/**
 * 作用: 根据一定的规则将 Stream 中的元素进行计算后再返回一个唯一的值
 * reduce 可接收一个, 两个以及三个参数
 * 1. 一个参数: 求和, 求最大值最小值以及将序列中满足某个条件的数筛出来做运算等
 * 2. 两个参数: 将对应的数据筛选出来进行连接并在最前面添加特定值
 * 3. 三个参数:
 */
@Slf4j
public class ReduceTest {

    private static final List<User> users = Lists.newArrayList();

    private static final String NAME_PREFIX = "name list is ";

    /**
     * test reduce method with three parameters
     *  <U> U reduce(U identity,
     *                  BiFunction<U, ? super T, U> accumulator,
     *                  BinaryOperator<U> combiner);
     *                  identity: 一个初始化的值；这个初始化的值其类型是泛型U，与Reduce方法返回的类型一致；注意此时Stream中元素的类型是T，与U可以不一样也可以一样，这样的话操作空间就大了
     *                  accumulator:
     */
    @Test
    public void testReduceWithThreeParameter() {

    }

    /**
     * test reduce method with two parameters
     */
    @Test
    public void testReduceWithTwoParameter() {
        // 收集集合中的所有名称并在前加上前缀
        String nameStr = users.stream().map(User::getChName).reduce(NAME_PREFIX, String::concat);
        System.out.println(nameStr);
    }

    /**
     * test reduce method with one parameter
     */
    @Test
    public void testReduceWithOneParameter() {
        // mapToDouble 实现工资求和
        Double sumSalaryByMapToDouble = users.stream().mapToDouble(User::getSalary).sum();
        log.info("sumSalaryByMapToDouble=====>" + sumSalaryByMapToDouble);
        assertTrue(51000 == sumSalaryByMapToDouble);
        // reduce 实现工资求和
        Optional<Double> sumSalaryByReduce = users.stream().map(User::getSalary).reduce(Double::sum);
        log.info("sumSalaryByReduce=====>" + sumSalaryByReduce);
        assertTrue(51000 == sumSalaryByReduce.get());

        // max 实现工资求最大值
        Optional<Double> maxSalaryByMax = users.stream().map(User::getSalary).max(Double::compareTo);
        log.info("maxSalaryByMax=====>" + maxSalaryByMax);
        assertTrue(28000D == maxSalaryByMax.get());
        // reduce 实现工资求最大值
        Optional<Double> maxSalaryByReduce = users.stream().map(User::getSalary).reduce((a, b) -> a >= b ? a : b);
        log.info("maxSalaryByReduce=====>" + maxSalaryByReduce);
        assertTrue(28000D == maxSalaryByReduce.get());

        // max 实现工资求小值
        Optional<Double> minSalaryByMax = users.stream().map(User::getSalary).min(Double::compareTo);
        log.info("minSalaryByMax=====>" + minSalaryByMax);
        assertTrue(8000D == minSalaryByMax.get());
        // reduce 实现工资求最小值
        Optional<Double> minSalaryByReduce = users.stream().map(User::getSalary).reduce((a, b) -> a >= b ? b : a);
        log.info("minSalaryByReduce=====>" + minSalaryByReduce);
        assertTrue(8000D == minSalaryByReduce.get());
    }

    /**
     * prepare test data
     */
    static {
        List<Address> zsAddressList = Lists.newArrayList();
        zsAddressList.add(Address.builder().country("中国").province("安徽省").city("合肥市").area("庐阳区").street("长江中路367号").build());
        zsAddressList.add(Address.builder().country("中国").province("安徽省").city("合肥市").area("包河区").street("桐城南路303号").build());
        List<Address> lsAddressList = Lists.newArrayList();
        lsAddressList.add(Address.builder().country("中国").province("安徽省").city("安庆市").area("大观区").street("纺织南路118号").build());
        lsAddressList.add(Address.builder().country("中国").province("安徽省").city("安庆市").area("宜秀区").street("集贤北路1318号").build());
        List<Address> wwAddressList = Lists.newArrayList();
        wwAddressList.add(Address.builder().country("中国").province("上海市").city("上海市").area("闵行区").street("联友路58号").build());
        wwAddressList.add(Address.builder().country("中国").province("上海市").city("上海市").area("杨浦区").street("政民路507号").build());
        users.add(User.builder().chName("张三").enName("zhangsan").age(18).salary(8000.00D).address(zsAddressList).build());
        users.add(User.builder().chName("李四").enName("lisi").age(23).salary(15000.00D).address(lsAddressList).build());
        users.add(User.builder().chName("王五").enName("wangwu").age(34).salary(28000.00D).address(wwAddressList).build());
    }
}
