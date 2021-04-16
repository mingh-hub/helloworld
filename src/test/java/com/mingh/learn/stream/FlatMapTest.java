package com.mingh.learn.stream;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.mingh.learn.beans.Address;
import com.mingh.learn.beans.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据扁平化处理
 */
@Slf4j
public class FlatMapTest {

    private static final List<User> users = Lists.newArrayList();

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

    @Test
    public void filterUserAddress() {
        List<List<Address>> addressList = users.stream()
                .map(User::getAddress)
                .peek(addresses -> log.info("addresses is {}", addresses))
                .collect(Collectors.toList());
        log.info("filterUserAddress result is {}", JSON.toJSONString(addressList));
    }

    @Test
    public void filterUserAddressWithFlatMap() {
        List<Address> addressList = users.stream()
                .map(User::getAddress)
                .flatMap(Collection::stream)
                .peek(address -> log.info("address is {}", address))
                .collect(Collectors.toList());
        log.info("filterUserAddress result is {}", JSON.toJSONString(addressList));
    }
}
