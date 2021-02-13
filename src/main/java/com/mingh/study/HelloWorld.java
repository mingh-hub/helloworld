package com.mingh.study;

import org.apache.commons.lang3.StringUtils;

public class HelloWorld {

    public static void main(String[] args) {
        System.out.println(filterAddressUnsupportChar("aaa123"));

        HelloWorld helloWorld = (HelloWorld) null;
        System.out.println(helloWorld);
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