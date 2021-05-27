package com.mingh.learn.jdbc;

import com.mingh.learn.common.constant.CommonConstants;
import com.mingh.learn.jdbc.bean.SqlBean;
import com.mingh.learn.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
        SqlBean bean = SqlBean.builder()
                .name("王五")
                .age(24)
                .birthday(LocalDate.of(1990, 11, 27))
                .description("是个爱打麻将的人")
                .createTime(TimeUtils.now())
                .updateTime(TimeUtils.now())
                .build();
        demo.add(bean);
    }
}
