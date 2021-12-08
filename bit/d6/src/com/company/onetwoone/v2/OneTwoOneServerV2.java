package com.company.onetwoone.v2;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class OneTwoOneServerV2 {

    //bad code
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9000);

        System.out.println("ready..............");
        Socket client = serverSocket.accept();
        Scanner inScanner = new Scanner(client.getInputStream());

        OutputStream out = client.getOutputStream();

        while (true) {
            String line = inScanner.nextLine();
            System.out.println("client message: " + line);

            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000);
                out.write((line + "\n").getBytes());
            }

        }//end while
    }
}
