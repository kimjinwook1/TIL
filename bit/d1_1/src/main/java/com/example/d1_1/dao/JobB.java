package com.example.d1_1.dao;

public class JobB implements AutoCloseable {

    @Override
    public void close() throws Exception {
        System.out.println("jobB close...........");
    }
}
