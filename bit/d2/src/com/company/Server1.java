package com.company;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server1 {
    //bad code
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("ready........");

        Socket client = serverSocket.accept();
        System.out.println("client........ " + client);

        InputStream in = client.getInputStream();
        Scanner inScanner = new Scanner(in);
        OutputStream out = client.getOutputStream();

        while (true) {
            String clientMsg = inScanner.nextLine();
            System.out.println(clientMsg);

            byte[] arr = (clientMsg + "\n").getBytes();
            out.write(arr);
        }
    }
}
