package com.company;

import java.io.FileOutputStream;
import java.io.InputStream;

public class KeyIn {

    //bad code
    public static void main(String[] args) throws Exception {

        InputStream in = System.in;
        FileOutputStream out = new FileOutputStream("/Users/kimjinwook/zzz/sample.txt");

        System.out.println("Insert your message");
        for (int i = 0; i < 100; i++) {
            int data = in.read();
            out.write(data);
        }

    }
}
