package com.company;

import java.io.File;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Main2 {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("ready.........");
        File targetFile = new File("/Users/kimjinwook/zzz/test.mp3");

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                OutputStream out = socket.getOutputStream();
                System.out.println("socket = " + socket);

                out.write(new String("HTTP/1.1 200 OK\r\n").getBytes());
                out.write(new String("Cache-Control: private\r\n").getBytes());
                out.write(new String("Content-Length: " + targetFile.length() + "\r\n").getBytes());
                out.write(new String("Content-Type: audio/mpeg\r\n\r\n").getBytes());
                //Java에서 파일 복사를 쉽게 하는 기능 제공
                Files.copy(targetFile.toPath(), out);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}