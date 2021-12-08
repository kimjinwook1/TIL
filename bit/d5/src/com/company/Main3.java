package com.company;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Main3 {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("ready..............");

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println(socket);

                InputStream in = socket.getInputStream();
                byte[] arr = new byte[50];

                int count = in.read(arr); //몇 개나 새로운 데이터가 왔는지
                System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
                String str = new String(arr);
                System.out.println("str = " + str);
                System.out.println("---------------------");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }//end while

    }//end main
}
