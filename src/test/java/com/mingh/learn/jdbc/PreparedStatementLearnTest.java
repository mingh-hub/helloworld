package com.mingh.learn.jdbc;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.mingh.learn.common.constant.CommonConstants;
import com.mingh.learn.jdbc.bean.SqlBean;
import com.mingh.learn.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.List;

/**
 * @ClassName PreparedStatementLearnTest
 * @Author Hai.Ming
 * @Date 2021/5/26 20:52
 * @Description test for PreparedStatement
 */
@Slf4j
public class PreparedStatementLearnTest {

    private PreparedStatementLearn preparedStatementLearn;

    @BeforeEach
    public void setUp() throws Exception {
        // 加载数据库驱动程序
        Class.forName(CommonConstants.DB_DRIVER);
        // 连接数据库
        Connection conn = DriverManager.getConnection(CommonConstants.DB_URL, CommonConstants.DB_NAME, CommonConstants.DB_PASSWORD);
        preparedStatementLearn = PreparedStatementLearn.builder()
                .dbDriver(CommonConstants.DB_DRIVER)
                .dbUrl(CommonConstants.DB_URL)
                .userName(CommonConstants.DB_NAME)
                .password(CommonConstants.DB_PASSWORD)
                .conn(conn)
                .build();
    }

    /**
     * @MethodName testQueryAll
     * @Author Hai.Ming
     * @Date 2021/5/30 13:12
     * @Description 测试查询所有数据及对返回数据的处理
     **/
    @Test
    public void testQueryAll() throws Exception {
        List<SqlBean> beans = preparedStatementLearn.queryAll();
        log.info("查询所有数据, 结果: {}", JSON.toJSONString(beans));
    }

    /**
     * @MethodName testUpdate
     * @Author Hai.Ming
     * @Date 2021/5/28 20:50
     * @Description 测试更新
     **/
    @Test
    public void testUpdate() throws Exception {
        preparedStatementLearn.updateById(2, "是个混吃等死的人");
    }

    /**
     * @MethodName testBatchAddWithException
     * @Author Hai.Ming
     * @Date 2021/5/28 19:45
     * @Description 测试批量新增报错
     **/
    @Test
    public void testBatchAddWithException() throws Exception {
        List<SqlBean> beanList = Lists.newArrayList();
        beanList.add(this.buildSqlBean("孙悟空", 102, LocalDate.of(1990, 11, 27), "是个爱打妖怪的人"));
        beanList.add(this.buildSqlBean("唐僧", 88, LocalDate.of(1990, 11, 27), "是个爱取经的人"));

        preparedStatementLearn.batchAddWithException(beanList);
    }

    /**
     * @MethodName testBatchAdd
     * @Author Hai.Ming
     * @Date 2021/5/28 19:32
     * @Description 测试批量新增
     **/
    @Test
    public void testBatchAdd() throws Exception {
        List<SqlBean> beanList = Lists.newArrayList();
        beanList.add(this.buildSqlBean("李四", 33, LocalDate.of(1988, 5, 27), "是个爱吹牛的人"));
        beanList.add(this.buildSqlBean("王五", 41, LocalDate.of(1980, 7, 15), "是个爱做梦的人"));
        beanList.add(this.buildSqlBean("孙悟空", 102, LocalDate.of(1990, 11, 27), "是个爱打妖怪的人"));
        beanList.add(this.buildSqlBean("唐僧", 88, LocalDate.of(1990, 11, 27), "是个爱取经的人"));
        preparedStatementLearn.batchAdd(beanList);
    }

    /**
     * @MethodName testAdd
     * @Author Hai.Ming
     * @Date 2021/5/28 19:32
     * @Description 测试单个新增
     **/
    @Test
    public void testAdd() throws Exception {
        preparedStatementLearn.add(this.buildSqlBean("张三", 24, LocalDate.of(1990, 11, 27), "是个爱打麻将的人"));
    }

    private SqlBean buildSqlBean(String name, int age, LocalDate birthday, String desc) {
        return SqlBean.builder()
                .name(name)
                .age(age)
                .birthday(birthday)
                .description(desc)
                .createTime(TimeUtils.now())
                .updateTime(TimeUtils.now())
                .build();
    }
}
