package com.company.onetwoone.v2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class OneTwoOneClientV2 {

    //bad code
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 9000);

        OutputStream out = socket.getOutputStream();
        Scanner keyScanner = new Scanner(System.in);
        Scanner inScanner = new Scanner(socket.getInputStream());

        new Thread(() -> {

            while (true) {

                System.out.println("send your message..");
                String msg = keyScanner.nextLine() + "\n";
                try {
                    out.write(msg.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }//계속해서 메시지를 입력하고 전송, end while
        }).start();

        while (true) {
            String serverMsg = inScanner.nextLine();
            System.out.println(serverMsg);
        }//end while

    }
}
