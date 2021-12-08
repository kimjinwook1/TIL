package com.company;

public class Ex1 {

    public void printLoop() {

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }).start();
    }

    public static void main(String[] args) {
        Ex1 obj = new Ex1();
        obj.printLoop();
        obj.printLoop();
        obj.printLoop();
    }
}
