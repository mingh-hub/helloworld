package com.mingh.learn.jdbc;

import com.mingh.learn.common.constant.CommonConstants;
import com.mingh.learn.jdbc.bean.SqlBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName StatementLearnTest
 * @Author Hai.Ming
 * @Date 2021/5/25 20:56
 * @Description 测试 jdbc 连接 MySql 数据库
 */
@Slf4j
public class StatementLearnTest {

    private StatementLearn statementLearn;

    @Before
    public void setUp() {
        statementLearn = StatementLearn.builder()
                .dbDriver(CommonConstants.DB_DRIVER)
                .dbUrl(CommonConstants.DB_URL)
                .userName(CommonConstants.DB_NAME)
                .password(CommonConstants.DB_PASSWORD)
                .build();
    }

    /**
     * @MethodName testForStringInject
     * @Author Hai.Ming
     * @Date 2021/5/26 20:17
     * @Description Statement-存在 sql 注入问题
     **/
    @Test
    public void testForStringInject() throws Exception {
        // 按如下拼接方式会查出所有数据库的结果
        // 同样原理, 如果更新也进行 sql 注入的话, 数据库数据全部都会被更新
        String userName = "张三' OR '1'='1";
        String connSql = "select id, name, age, birthday, description, create_time, update_time from t_learn_jdbc_base where name='" + userName + "'";
        List<SqlBean> result = statementLearn.queryWithSql(connSql);
        log.info("查询结果:{}", result);
    }

    /**
     * @MethodName testForStringJoint
     * @Author Hai.Ming
     * @Date 2021/5/25 21:08
     * @Description Statement-存在拼串操作
     **/
    @Test
    public void testForStringJoint() throws Exception {
        // 正常查询没问题
        String sql = "select id, name, age, birthday, description, create_time, update_time from t_learn_jdbc_base where name='张三'";
        List<SqlBean> result = statementLearn.queryWithSql(sql);
        log.info("正常查询结果:{}", result);
        // 如果是接收前端入参, 用 Statement 极可能出现由 sql 拼接所导致的问题。java.sql.SQLSyntaxErrorException
        String userName = "Mr'Smith";
        String connSql = "select id, name, age, birthday, description, create_time, update_time from t_learn_jdbc_base where name='" + userName + "'";
        List<SqlBean> connResult = statementLearn.queryWithSql(connSql);
        log.info("拼接字符串查询结果:{}", connResult);
    }
}
