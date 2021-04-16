package com.mingh.learn.beans;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class BaseBean implements Serializable {
    private static final long serialVersionUID = 6912250298324988931L;

    public String toString() {
        return JSON.toJSONString(this);
    }
}
