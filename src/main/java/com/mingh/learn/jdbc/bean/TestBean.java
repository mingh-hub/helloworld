package com.mingh.learn.jdbc.bean;

import com.mingh.learn.beans.BaseBean;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @ClassName TestBean
 * @Author Hai.Ming
 * @Date 2021/5/26 20:02
 * @Description 数据库测试对象
 */
@Builder
@Getter
@Setter
public class TestBean extends BaseBean {
    private Long id;
    private String name;
    private Integer age;
    private String description;
    private LocalDateTime birthday;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
