package com.mingh.learn.stream;

import com.google.common.collect.Lists;
import com.mingh.learn.common.enums.ResultEnum;
import com.mingh.learn.common.exception.BusinessRuntimeException;
import com.mingh.learn.beans.Address;
import com.mingh.learn.beans.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;

/**
 * 作用: 根据一定的规则将 Stream 中的元素进行计算后再返回一个唯一的值
 * reduce 可接收一个, 两个以及三个参数
 * 1. 一个参数: 求和, 求最大值最小值以及将序列中满足某个条件的数筛出来做运算等
 * 2. 两个参数: 将对应的数据筛选出来进行连接并在最前面添加特定值
 * 3. 三个参数: 非并行时和两个参数一致, 第三个参数不起作用; 并行时第三个参数才会起作用
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
     *                  accumulator: 其类型是 BiFunction, 输入的是 U 和 T 两种类型数据, 返回 U 类型数据。返回的类型和第一个参数类型一致, 第二个参数类型和 Stream 中元素类型一致
     *                  combiner: 其类型是 BinaryOperator，支持的是对 U 类型的对象进行操作; 并行操作时才起作用, 对各线程执行结果进行合并处理
     */
    @Test
    public void testReduceWithThreeParameter() {
        // 对元素进行收集, 收集集合中用户英文名称
        List<String> enNameList = users.stream()
                .map(User::getEnName)
                .reduce(Lists.newArrayList(),
                        (u, t) -> {
                            u.add(t); // 此处 u 类型和第一个输入参数的类型一致, 即为 List, t 和 Stream 中类型一致, 为 String 类型
                            return u;
                        },
                        (u1, u2) -> u1);  //
        log.info("enName list is {}", enNameList);
        // 对元素进行过滤, 筛选英文名称中包含 ang 的值, 使用串行流会达到预期效果, 结果为 [wangwu, zhangsan], 并行会多出很多重复结果
        Predicate<String> predicate = s -> s.contains("ang");
        List<String> enNameContainAngList = users.parallelStream()
                .map(User::getEnName)
                .reduce(Lists.newArrayList(),
                        (u, t) -> {
                            if (predicate.test(t)) {
                                u.add(t);
                            }
                            return u;
                        },
                        (u1, u2) -> {
                            u1.addAll(u2);  // 如果使用并行流, 此处会启动三个线程(parallelStream 使用 ForkJoin 框架实现, 默认启动线程数量为 cpu 数量)
                            return u2;  // 每个线程的执行结果为[wangwu, zhangsan], 合并计算为 [wangwu, zhangsan, wangwu, zhangsan, wangwu, zhangsan, wangwu, zhangsan]
                        });
        log.info("enNameContainAngList list is {}", enNameContainAngList);
    }

    /**
     * test reduce method with two parameters
     */
    @Test
    public void testReduceWithTwoParameter() {
        Predicate<List<User>> predicate = CollectionUtils::isNotEmpty;
        Optional.of(users).filter(predicate).orElseThrow(() -> new BusinessRuntimeException(ResultEnum.PARAMS_IS_MISSING));
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
        double sumSalaryByMapToDouble = users.stream().mapToDouble(User::getSalary).sum();
        log.info("sumSalaryByMapToDouble=====>" + sumSalaryByMapToDouble);
        assertEquals(51000, (double) sumSalaryByMapToDouble, 0.0);
        // reduce 实现工资求和
        Optional<Double> sumSalaryByReduce = users.stream().map(User::getSalary).reduce(Double::sum);
        log.info("sumSalaryByReduce=====>" + sumSalaryByReduce);
        assertEquals(51000, (double) sumSalaryByReduce.get(), 0.0);

        // max 实现工资求最大值
        Optional<Double> maxSalaryByMax = users.stream().map(User::getSalary).max(Double::compareTo);
        log.info("maxSalaryByMax=====>" + maxSalaryByMax);
        assertEquals(28000D, maxSalaryByMax.get(), 0.0);
        // reduce 实现工资求最大值
        Optional<Double> maxSalaryByReduce = users.stream().map(User::getSalary).reduce((a, b) -> a >= b ? a : b);
        log.info("maxSalaryByReduce=====>" + maxSalaryByReduce);
        assertEquals(28000D, maxSalaryByReduce.get(), 0.0);

        // max 实现工资求小值
        Optional<Double> minSalaryByMax = users.stream().map(User::getSalary).min(Double::compareTo);
        log.info("minSalaryByMax=====>" + minSalaryByMax);
        assertEquals(8000D, minSalaryByMax.get(), 0.0);
        // reduce 实现工资求最小值
        Optional<Double> minSalaryByReduce = users.stream().map(User::getSalary).reduce((a, b) -> a >= b ? b : a);
        log.info("minSalaryByReduce=====>" + minSalaryByReduce);
        assertEquals(8000D, minSalaryByReduce.get(), 0.0);
    }

    /**
     * prepare test data
     */
    static {
        List<Address> zsAddressList = new ArrayList<Address>(){
            {
                add(Address.builder().country("中国").province("安徽省").city("合肥市").area("庐阳区").street("长江中路367号").build());
                add(Address.builder().country("中国").province("安徽省").city("合肥市").area("包河区").street("桐城南路303号").build());
            }
        };
        List<Address> lsAddressList = new ArrayList<Address>() {
            {
                add(Address.builder().country("中国").province("安徽省").city("安庆市").area("大观区").street("纺织南路118号").build());
                add(Address.builder().country("中国").province("安徽省").city("安庆市").area("宜秀区").street("集贤北路1318号").build());
            }
        };
        List<Address> wwAddressList = new ArrayList<Address>() {
            {
                add(Address.builder().country("中国").province("上海市").city("上海市").area("闵行区").street("联友路58号").build());
                add(Address.builder().country("中国").province("上海市").city("上海市").area("杨浦区").street("政民路507号").build());
            }
        };
        users.add(User.builder().chName("张三").enName("zhangsan").age(18).salary(8000.00D).address(zsAddressList).build());
        users.add(User.builder().chName("李四").enName("lisi").age(23).salary(15000.00D).address(lsAddressList).build());
        users.add(User.builder().chName("王五").enName("wangwu").age(34).salary(28000.00D).address(wwAddressList).build());
    }
}
