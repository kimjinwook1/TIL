package com.company;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {

    //bad code
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(9000);

        System.out.println("ready.............");

        while(true){
            System.out.println("waiting.......socket connect");

            String name="아이유.jpg";
            byte[] arr = name.getBytes();

            byte[] nameArr = new byte[100];

            System.arraycopy(arr,0,nameArr,0, arr.length);


            try (Socket socket = serverSocket.accept();
                 OutputStream out = socket.getOutputStream();
                 FileInputStream fin = new FileInputStream("C:\\zzz\\target.jpg");
            ){

                byte[] buffer = new byte[1024 * 8];

                out.write(nameArr); //먼저 100바이트 전송

                while (true) {
                    int count = fin.read(buffer);
                    if (count == -1) {
                        break;
                    }
                    out.write(buffer, 0, count);
                }//end while
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }//end while


    }
}