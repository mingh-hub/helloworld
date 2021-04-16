package com.mingh.learn.beans;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address extends BaseBean{
    private static final long serialVersionUID = -9025878456374359713L;

    /**
     * 国籍
     */
    private String country;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区
     */
    private String area;
    /**
     * 街道
     */
    private String street;
}
