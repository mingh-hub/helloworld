package com.mingh.learn.jdbc;

import com.mingh.learn.common.enums.ResultEnum;
import com.mingh.learn.common.exception.BusinessRuntimeException;
import com.mingh.learn.jdbc.bean.TestBean;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

/**
 * @ClassName PreparedStatementDemo
 * @Author Hai.Ming
 * @Date 2021/5/26 20:32
 * @Description 通过 PreparedStatement 测试操作数据库
 */
@Slf4j
@Data
@Builder
public class PreparedStatementDemo {
    private String dbDriver;
    private String dbUrl;
    private String userName;
    private String password;

    /**
     * @MethodName insert
     * @Author Hai.Ming
     * @Date 2021/5/26 20:52
     * @Description PreparedStatement 新增操作
     **/
    public void insert(TestBean bean) throws Exception {
        if (Objects.isNull(bean)) {
            throw new BusinessRuntimeException(ResultEnum.PARAMS_IS_MISSING);
        }
        // 1. 加载数据库驱动程序
        Class.forName(dbDriver);
        // 2. 连接数据库
        Connection conn = DriverManager.getConnection(dbUrl, userName, password);
        // 3. 预处理数据
        String sql = "insert into test(id, name, age, birthday, description, create_time, update_time) values (myseq.nextval, ?, ?, ?, ?, ?, ?)";

    }
}
