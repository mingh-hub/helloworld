package com.mingh.learn.jdbc;

import com.mingh.learn.common.constant.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @ClassName PreparedStatementDemoTest
 * @Author Hai.Ming
 * @Date 2021/5/26 20:52
 * @Description test for PreparedStatement
 */
@Slf4j
public class PreparedStatementDemoTest {

    private PreparedStatementDemo demo;

    @BeforeEach
    public void setUp() {
        demo = PreparedStatementDemo.builder()
                .dbDriver(CommonConstants.DB_DRIVER)
                .dbUrl(CommonConstants.DB_URL)
                .userName(CommonConstants.DB_NAME)
                .password(CommonConstants.DB_PASSWORD)
                .build();
    }

    @Test
    public void testInsert() throws Exception {
        String sql = "insert into test(id, name, age, birthday, description, create_time, update_time) values (myseq.nextval, ?, ?, ?, ?, ?, ?)";
    }
}
