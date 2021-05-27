package com.mingh.learn.jdbc;

import com.mingh.learn.common.enums.ResultEnum;
import com.mingh.learn.common.exception.BusinessRuntimeException;
import com.mingh.learn.jdbc.bean.SqlBean;
import com.mingh.learn.utils.TimeUtils;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
     * @MethodName batchAdd
     * @Author Hai.Ming
     * @Date 2021/5/27 21:08
     * @Description PreparedStatement 批量新增
     **/
    public void batchAdd() {

    }

    /**
     * @MethodName add
     * @Author Hai.Ming
     * @Date 2021/5/26 20:52
     * @Description PreparedStatement 新增操作
     **/
    public boolean add(SqlBean bean) throws Exception {
        if (Objects.isNull(bean)) {
            throw new BusinessRuntimeException(ResultEnum.PARAMS_IS_MISSING);
        }
        // 加载数据库驱动程序
        Class.forName(dbDriver);
        // 连接数据库
        Connection conn = DriverManager.getConnection(dbUrl, userName, password);
        // 预处理数据
        String sql = "insert into test(id, name, age, birthday, description, create_time, update_time) values (nextval('seq_dev_num'), ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, bean.getName());
        pstmt.setInt(2, bean.getAge());
        pstmt.setDate(3, TimeUtils.toSqlDate(bean.getBirthday()));
        pstmt.setString(4, bean.getDescription());
        pstmt.setTimestamp(5, TimeUtils.toSqlTimestamp(TimeUtils.now()));
        pstmt.setTimestamp(6, TimeUtils.toSqlTimestamp(TimeUtils.now()));
        // 执行
        return pstmt.execute();
    }
}
