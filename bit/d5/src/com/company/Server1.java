package com.company;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Server1 {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("ready..............");

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println(socket);
                Scanner scanner = new Scanner(socket.getInputStream());
                OutputStream out = socket.getOutputStream();

                //split
                String line = scanner.nextLine();
                String[] lineArr = line.split(" ");
                String fileName = lineArr[1];

                String msg = "<h1>" + fileName + "</h1>";

                out.write(new String("HTTP/1.1 200 OK\r\n").getBytes());
                out.write(new String("Cache-Control: private\r\n").getBytes());
                out.write(new String("Content-Length: " + msg.getBytes("UTF-8").length + "\r\n").getBytes());
                out.write(new String("Content-Type: text/html; charset=UTF-8\r\n\r\n").getBytes());
                out.write(msg.getBytes("UTF-8"));

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }//end while

    }//end main
}
