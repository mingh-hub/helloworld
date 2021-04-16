package com.mingh.learn;

import org.apache.commons.lang3.StringUtils;

public class HelloWorld {

    public static void main(String[] args) {
        try {
            int a = 4 / 0;
        } catch (ClassCastException e) {
            System.out.println(e);
        }
        finally {

            System.out.println("==================");
        }
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