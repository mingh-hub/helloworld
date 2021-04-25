package com.mingh.learn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.mingh.learn.beans.Book;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class HelloWorld {

    public static void main(String[] args) {
        Book book = Book.builder().bookName("test01").build();
        List<Book> bookList = Lists.newArrayList();
        bookList.add(book);
        String jsonData = JSON.toJSONString(bookList);
        System.out.println(jsonData);

        List<Book> list = JSONArray.parseArray(jsonData, Book.class);
        System.out.println(list);

    }

    private static String filterAddressUnsupportChar(String address) {
        if (StringUtils.isEmpty(address)) {
            return address;
        }
        String tmpStr = address.trim();
        String tmpStr1 = tmpStr.replaceAll("\\d+", "");

        tmpStr = tmpStr.replaceAll("\\d+", "").replaceAll("[A-Za-z]", "").replaceAll("[\\u4e00-\\u9fa5]", "");
        tmpStr = tmpStr.replaceAll("#", "").replaceAll("-", "").replaceAll("_", "");
        char[] filterChars = tmpStr.toCharArray();
        for (int i = 0; i < filterChars.length; i++) {
            address = address.replaceAll("[" + String.valueOf(filterChars[i]) + "]", "");
        }
        return address.replaceAll(" ", "");
    }
}