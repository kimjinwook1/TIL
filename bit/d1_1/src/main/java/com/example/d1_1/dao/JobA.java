package com.example.d1_1.dao;

public class JobA implements AutoCloseable{
    public int doA(int a, int b) {
        return a/b;
    }

    @Override
    public void close() throws Exception {
        System.out.println("jobA close...........");
    }
}
