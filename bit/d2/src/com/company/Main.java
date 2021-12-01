package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Main {

    //bad code
    public static void main(String[] args) throws Exception {

//        URL url = new URL("https://postfiles.pstatic.net/MjAyMTExMjlfNzEg/MDAxNjM4MTc3MjIyODAy.KteUW_rbf_Wgbfv6TDIqp-Fk5QBi8Rg3TjJ1MYrgkyYg.ZuuEsLqfRE7lcSjTBLFoUgKf7bIUCkHlS8fT8ceLQ8sg.JPEG.kimdarn/SE-d03a9a33-e084-430b-a9c9-7b2313b49422.jpg");
//        InputStream in = url.openStream();
        InputStream in = new FileInputStream("/Users/kimjinwook/zzz/bbb.jpeg");
        OutputStream out = new FileOutputStream("/Users/kimjinwook/zzz/copy.jpeg");

        long start = System.currentTimeMillis();

        while (true) {
            int content = in.read();
//            System.out.println(content);
            if (content == -1) {
                break;
            }
            out.write(content);
        }//end while

        long end = System.currentTimeMillis();
        System.out.println("TOTAL: " + (end - start));
    }
}
