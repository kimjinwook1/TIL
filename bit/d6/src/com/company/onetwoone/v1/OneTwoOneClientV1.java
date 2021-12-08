package com.company.onetwoone.v1;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class OneTwoOneClientV1 {

    //bad code
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 9000);

        OutputStream out = socket.getOutputStream();
        Scanner keyScanner = new Scanner(System.in);

        while (true) {

            System.out.println("send your message..");
            String msg = keyScanner.nextLine() + "\n";
            out.write(msg.getBytes());

        }//계속해서 메시지를 입력하고 전송, end while
    }
}
