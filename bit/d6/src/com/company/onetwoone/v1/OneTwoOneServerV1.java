package com.company.onetwoone.v1;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class OneTwoOneServerV1 {

    //bad code
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9000);

        System.out.println("ready..............");
        Socket client = serverSocket.accept();
        Scanner inScanner = new Scanner(client.getInputStream());

        while (true) {
            String line = inScanner.nextLine();
            System.out.println("client message: " + line);

        }//end while
    }
}
