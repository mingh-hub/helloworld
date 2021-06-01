package com.mingh.learn.jdbc;

import com.google.common.collect.Lists;
import com.mingh.learn.jdbc.bean.SqlBean;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName StatementLearn
 * @Author Hai.Ming
 * @Date 2021/5/25 20:40
 * @Description 通过 Statement 测试操作数据库
 */
@Slf4j
@Getter
@Builder
public class StatementLearn {

    private String dbDriver;
    private String dbUrl;
    private String userName;
    private String password;

    /**
     * @MethodName queryWithSql
     * @Author Hai.Ming
     * @Date 2021/5/26 19:54
     * @Description Statement 查询操作
     **/
    public List<SqlBean> queryWithSql(String sql) throws Exception {
        // 1. 加载数据库驱动程序
        Class.forName(dbDriver);
        // 2. 连接数据库
        Connection conn = DriverManager.getConnection(dbUrl, userName, password);
        // 3. 操作数据库
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<SqlBean> beans = Lists.newArrayList();
        while (Objects.nonNull(rs) && rs.next()) {
            beans.add(SqlBean.builder()
                    .id(rs.getLong(1))
                    .name(rs.getString(2))
                    .age(rs.getInt(3))
                    .birthday(rs.getObject(4, LocalDate.class))
                    .description(rs.getString(5))
                    .createTime(rs.getObject(6, LocalDateTime.class))
                    .updateTime(rs.getObject(7, LocalDateTime.class))
                    .build());
        }
        conn.close();
        return beans;
    }

    /**
     * @MethodName updateWithSql
     * @Author Hai.Ming
     * @Date 2021/5/25 20:48
     * @Description Statement 更新操作
     **/
    public void updateWithSql(String sql) throws Exception {
        // 1. 加载数据库驱动程序
        Class.forName(dbDriver);
        // 2. 连接数据库
        Connection conn = DriverManager.getConnection(dbUrl, userName, password);
        // 3. 操作数据库
        Statement statement = conn.createStatement();
        int i = statement.executeUpdate(sql);
        log.info("此次操作影响的行数: {}", i);
        conn.close();
    }
}
