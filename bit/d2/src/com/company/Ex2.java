package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class Ex2 {

    //bad code
    public static void main(String[] args) throws IOException {

        String str = "가나다라마";
        byte[] arr1 = str.getBytes(); //String을 byte[]로 변경
        System.out.println(Arrays.toString(arr1));

        String result = new String(arr1); //byte[]을 문자열로 변경
        System.out.println(result);

//        OutputStream out = new FileOutputStream("/Users/kimjinwook/zzz/day2.txt");
//
//        for (int i = 0; i < arr1.length; i++) {
//            out.write(arr1[i]);
//        }
    }
}
