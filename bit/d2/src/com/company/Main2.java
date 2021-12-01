package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


public class Main2 {
    public static void main(String[] args) throws Exception {

        byte[] buffer = new byte[1024 * 8]; //8kb
        InputStream in = new FileInputStream("/Users/kimjinwook/zzz/bbb.jpeg");
        OutputStream out = new FileOutputStream("/Users/kimjinwook/zzz/copy.jpg");
        long start = System.currentTimeMillis();

        while (true) {
            int count = in.read(buffer);
            //System.out.println(count);
            //System.out.println(Arrays.toString(buffer));

            if (count == -1) {
                break;
            }

            out.write(buffer, 0, count);

        }//end while

        long end = System.currentTimeMillis();

        System.out.println("TOTAL: " + (end - start));
    }
}
