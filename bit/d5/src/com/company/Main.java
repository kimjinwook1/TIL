package com.company;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    //bad code
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("ready..............");

        while(true){
            try(
                    Socket socket = serverSocket.accept();
                    OutputStream out = socket.getOutputStream();
            ) {
                System.out.println(socket);
                String msg = "<h1>홍길동</h1>";

                out.write(new String("HTTP/1.1 200 OK\r\n").getBytes());
                out.write(new String("Cache-Control: private\r\n").getBytes());
                out.write(new String("Content-Length: " + msg.getBytes("UTF-8").length + "\r\n").getBytes());
                out.write(new String("Content-Type: text/html; charset=UTF-8\r\n\r\n").getBytes());
                out.write(msg.getBytes("UTF-8"));

            }catch(Exception e){
                e.printStackTrace();
            }
        }//end while

    }
}