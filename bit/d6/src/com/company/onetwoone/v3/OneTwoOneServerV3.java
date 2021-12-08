package com.company.onetwoone.v3;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class OneTwoOneServerV3 {

    //bad code
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9000);

        System.out.println("ready..............");
        Socket client = serverSocket.accept();
        Scanner inScanner = new Scanner(client.getInputStream());

        OutputStream out = client.getOutputStream();
        Scanner keyScanner = new Scanner(System.in);

        new Thread(() -> {

            while (true) {
                System.out.println("send your message..");
                String serverMsg = keyScanner.nextLine() + "\n";

                try {
                    out.write(serverMsg.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        while (true) {
            String line = inScanner.nextLine();
            System.out.println("client message: " + line);

//                for (int i = 0; i < 5; i++) {
//                    try {
//                        Thread.sleep(1000);
//                        out.write((line + "\n").getBytes());
//                    } catch (InterruptedException | IOException e) {
//                        e.printStackTrace();
//                    }
//                }
        }//end while

    }
}
