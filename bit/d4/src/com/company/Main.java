package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // write your code here
        String name="target.jpg";
        byte[] arr = name.getBytes();

        byte[] nameArr = new byte[100];

        System.arraycopy(arr,0,nameArr,0, arr.length);

        System.out.println(Arrays.toString(nameArr));

    }
}