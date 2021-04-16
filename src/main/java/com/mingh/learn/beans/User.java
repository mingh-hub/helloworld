package com.mingh.learn.beans;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User extends BaseBean {
    private static final long serialVersionUID = 4262652985755035330L;

    /**
     * 用户中文名
     */
    private String chName;
    /**
     * 用户英文名
     */
    private String enName;
    /**
     * 用户年龄
     */
    private Integer age;
    /**
     * 用户月收入
     */
    private Double salary;
    /**
     * 用户地址, 可能存在多个
     */
    private List<Address> address;
}
