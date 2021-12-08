package com.company;

public class Horse extends Thread {

    private int pos;
    private String name;

    public Horse(String name) {
        this.name = name;
        this.pos = 0;
    }

    @Override
    public void run() {
        gallop();
    }

    public void gallop() {
        for (int i = 0; i < 100; i++) {
            this.pos += (int) (Math.random() * 10);

            int dotCount = this.pos / 10;

            synchronized (System.out) {

                for (int j = 0; j < dotCount; j++) {
                    System.out.print(".");
                }

                System.out.println(name + ": " + pos);

            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
