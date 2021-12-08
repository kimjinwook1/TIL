package com.company;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Ex3 {

    //bad code
    public static void main(String[] args) throws Exception {


        for (int i = 0; i < 100; i++) {

            new Thread(() -> {

                try {
                    URL url = new URL("url");
                    InputStream in = url.openStream();
                    System.out.println(Thread.currentThread().getContextClassLoader() + " url = " + url);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }
}
