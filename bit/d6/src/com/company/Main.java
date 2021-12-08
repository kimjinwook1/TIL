package com.company;

public class Main {

    public static void main(String[] args) {

//        Ex2 obj = new Ex2();
//
//        new Thread(obj).start();
//        new Thread(obj).start();
//        new Thread(obj).start();
//        new Thread(obj).start();

        Horse h1 = new Horse("1번마");
        Horse h2 = new Horse("2번마");
        Horse h3 = new Horse("3번마");
        Horse h4 = new Horse("4번마");

        h1.start(); //Thread의 메서드 start()를 통해 실행 -> Horse클래스의 run()이 실행됨
        h2.start();
        h3.start();
        h4.start();

    }
}