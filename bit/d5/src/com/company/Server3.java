package com.company;

import java.io.File;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Server3 {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("ready..............");

        Map<String, String> mimeMap = new HashMap<>();
        mimeMap.put("html", "text/html; charset=UTF-8");
        mimeMap.put("jpg", "image/jpeg");
        mimeMap.put("mp3", "audio/mpeg");

        while (true) {
            try {
                //split
                Socket socket = serverSocket.accept();

                new Thread(() -> {

                    try {
                        Scanner scanner = new Scanner(socket.getInputStream());
                        OutputStream out = socket.getOutputStream();
                        String line = scanner.nextLine();
                        String[] lineArr = line.split(" ");
                        String fileName = lineArr[1];

                        String exp = fileName.substring(fileName.indexOf(".") + 1);
                        String mimeType = mimeMap.get(exp);
                        if (mimeType != null) {

                            System.out.println("mimeType = " + mimeType);
                        }

                        Thread.sleep(3000);

                        File targetFile = new File("/Users/kimjinwook/zzz/" + fileName); // C:\\zzz/bbb.jpg

                        out.write(new String("HTTP/1.1 200 OK\r\n").getBytes());
                        out.write(new String("Cache-Control: private\r\n").getBytes());
                        out.write(new String("Content-Length: " + targetFile.length() + "\r\n").getBytes());
                        out.write(new String("Content-Type: " + mimeType + "\r\n\r\n").getBytes());
                        Files.copy(targetFile.toPath(), out);

                        out.close();
                        socket.close();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }).start();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }//end while

    }//end main
}
