package com.company;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
    //bad code
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("192.168.1.67", 9090);
        System.out.println(socket);
        OutputStream out = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        Scanner inScanner = new Scanner(inputStream);
        Scanner keyScanner = new Scanner(System.in);

        while (true) {
            System.out.println("보내고 싶은 메시지");
            String msg = keyScanner.nextLine() + "\n";
            byte[] arr = msg.getBytes();

            out.write(arr);

            System.out.println(inScanner.nextLine());
        }
    }
}
