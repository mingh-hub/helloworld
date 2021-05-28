package com.mingh.learn.jdbc;

import com.mingh.learn.common.enums.ResultEnum;
import com.mingh.learn.common.exception.BusinessRuntimeException;
import com.mingh.learn.jdbc.bean.SqlBean;
import com.mingh.learn.utils.TimeUtils;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
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
     * @MethodName batchAddWithException
     * @Author Hai.Ming
     * @Date 2021/5/28 19:48
     * @Description 测试批量新增出错, 是否开启自动提交
     **/
    public void batchAddWithException(List<SqlBean> beans) throws Exception {
        if (CollectionUtils.isEmpty(beans)) {
            throw new BusinessRuntimeException(ResultEnum.PARAMS_IS_MISSING);
        }
        // 加载数据库驱动程序
        Class.forName(dbDriver);
        // 连接数据库
        Connection conn = DriverManager.getConnection(dbUrl, userName, password);
        conn.setAutoCommit(false);
        try {
            // 预处理数据
            String sql = "insert into test(id, name, age, birthday, description, create_time, update_time) values (nextval('seq_dev_num'), ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < beans.size(); i++) {
                pstmt.setString(1, beans.get(i).getName());
                pstmt.setInt(2, beans.get(i).getAge());
                pstmt.setDate(3, TimeUtils.toSqlDate(beans.get(i).getBirthday()));
                pstmt.setString(4, beans.get(i).getDescription());
                pstmt.setTimestamp(5, TimeUtils.toSqlTimestamp(TimeUtils.now()));
                pstmt.setTimestamp(6, TimeUtils.toSqlTimestamp(TimeUtils.now()));
                pstmt.addBatch();
                if (i == 0) {
                    // 中间增加一条错误的 sql, 自动提交开启, 错误 sql 不会影响正确 sql 执行; 自动提交关闭后, 只要有失败则会回滚
                    pstmt.addBatch(sql);
                }
            }
            pstmt.executeBatch();
            conn.commit();
        } catch (Exception e) {
            log.error("PreparedStatementDemo batchAddWithException execute error.", e);
            conn.rollback();
        }
        conn.close();
    }

    /**
     * @MethodName batchAdd
     * @Author Hai.Ming
     * @Date 2021/5/27 21:08
     * @Description PreparedStatement 批量新增
     **/
    public void batchAdd(List<SqlBean> beans) throws Exception {
        if (CollectionUtils.isEmpty(beans)) {
            throw new BusinessRuntimeException(ResultEnum.PARAMS_IS_MISSING);
        }
        // 加载数据库驱动程序
        Class.forName(dbDriver);
        // 连接数据库
        Connection conn = DriverManager.getConnection(dbUrl, userName, password);
        // 预处理数据
        String sql = "insert into test(id, name, age, birthday, description, create_time, update_time) values (nextval('seq_dev_num'), ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (SqlBean bean : beans) {
            pstmt.setString(1, bean.getName());
            pstmt.setInt(2, bean.getAge());
            pstmt.setDate(3, TimeUtils.toSqlDate(bean.getBirthday()));
            pstmt.setString(4, bean.getDescription());
            pstmt.setTimestamp(5, TimeUtils.toSqlTimestamp(TimeUtils.now()));
            pstmt.setTimestamp(6, TimeUtils.toSqlTimestamp(TimeUtils.now()));
            pstmt.addBatch();
        }
        pstmt.executeBatch();
        conn.close();
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
        boolean result = pstmt.execute();
        conn.close();
        return result;
    }
}
