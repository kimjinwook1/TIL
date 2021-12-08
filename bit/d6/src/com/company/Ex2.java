package com.company;

public class Ex2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(this +": " +Thread.currentThread().getName() + i);
        }
    }
}
