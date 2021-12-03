package com.company;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
public class Client1 {

    //bad code
    public static void main(String[] args) throws Exception{

        Socket socket = new Socket(/*"아이피주소", 포트번호*/"localhost",9000);

        OutputStream out = socket.getOutputStream();
        System.out.println(socket);
        InputStream in = socket.getInputStream();
        Scanner inScanner = new Scanner(in);

        String name = "김진욱\n";
        out.write(name.getBytes());
        System.out.println("-----------------------------");
        String serverMsg = inScanner.nextLine();
        System.out.println("SERVER: " + serverMsg);

    }

}
