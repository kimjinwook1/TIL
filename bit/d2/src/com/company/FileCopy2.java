package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class FileCopy2 {

    //bad code
    public static void main(String[] args) throws Exception {

        byte[] buffer = new byte[5];

        InputStream in = new FileInputStream("/Users/kimjinwook/zzz/target.txt");
        OutputStream out = new FileOutputStream("/Users/kimjinwook/zzz/targetCopy.txt");

        while (true) {

            int count = in.read(buffer);
            System.out.println(count);
            System.out.println(Arrays.toString(buffer));

            if (count == -1) {
                break;
            }

            out.write(buffer, 0, count);

        }//end while
    }
}
