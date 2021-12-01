package com.company;

import com.company.game.Game;
import com.company.game.GameInjection;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server1 {

    public static void main(String[] args) throws Exception {

        //무상태
        ServerSocket serverSocket = new ServerSocket(9000); // 9000번대의 나의 소켓을 열어둠

        GameInjection gameInjection = new GameInjection();
        Game game = gameInjection.game();

        System.out.println("ready............");

        while (true) {

            Socket socket = serverSocket.accept();
            System.out.println(socket);

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            Scanner scanner = new Scanner(in);

            try {
                String line = scanner.nextLine();
                System.out.println(line);

                out.write((game.play(line) + "\n").getBytes());
                out.flush();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }//end while

    }
}
