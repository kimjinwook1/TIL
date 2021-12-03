package com.company;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class FileClient {

    //bad code
    public static void main(String[] args) throws Exception {


        Socket socket = new Socket("192.168.0.141", 9000);
        InputStream in = socket.getInputStream();

        byte[] nameArr = new byte[100];

        in.read(nameArr);

        String fileName = new String(nameArr).trim();

        System.out.println("fileName = " + fileName);

        FileOutputStream fos = new FileOutputStream("/Users/kimjinwook/zzz2/" + fileName);

        byte[] buffer = new byte[1024 * 8];
        while (true) {
            int count = in.read(buffer);
            if (count == -1) {
                break;
            }

            fos.write(buffer, 0, count);
        }
    }
}
