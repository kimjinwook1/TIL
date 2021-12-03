package com.example.d1_1.dao;

import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreDAO {

    public int calcGap1(String from, String to) throws Exception {
        int result = 0;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement("SELECT TIMESTAMPDIFF(DAY, ?, ?)");
            preparedStatement.setString(1, from);
            preparedStatement.setString(2, to);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();

            result = resultSet.getInt(1);

            resultSet.close();
            resultSet = null;
            preparedStatement.close();
            preparedStatement = null;
            connection.close();
            connection = null;


        } catch (Exception e) {
            throw e;

        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }

        return result;
    }

    // 단점 -> preparedStatement을 처리하기위해 따로 makePrepare메서드를 만들어야한다는 점이 있다.
    public int calcGap2(String from, String to) throws Exception {
        int result = 0;
        try (Connection connection = ConnectionUtil.INSTANCE.getConnection();
             PreparedStatement preparedStatement = makePrepare(connection, from, to);
             ResultSet resultSet = preparedStatement.executeQuery()

        ) {
            resultSet.next();
            result = resultSet.getInt(1);

        }
        return result;
    }

    private PreparedStatement makePrepare(Connection connection, String from, String to) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT TIMESTAMPDIFF(DAY, ?, ?)");
        preparedStatement.setString(1, from);
        preparedStatement.setString(2, to);
        return preparedStatement;

    }

    public int calcGap3(String from, String to) throws Exception {

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("SELECT TIMESTAMPDIFF(DAY, ?, ?)");
        preparedStatement.setString(1, from);
        preparedStatement.setString(2, to);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        @Cleanup JobA jobA = new JobA();
        @Cleanup JobB jobb = new JobB();

        resultSet.next();
        return resultSet.getInt(1);
    }
}
