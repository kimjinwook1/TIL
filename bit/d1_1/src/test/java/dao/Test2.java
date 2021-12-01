package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test2 {

    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    String url = "jdbc:mysql://192.168.0.189:3306/webdb";

                    Connection connection = DriverManager.getConnection(url, "webuser", "webuser");

                    System.out.println(connection);

                    PreparedStatement preparedStatement = connection.prepareStatement("select now()");
                    ResultSet resultSet = preparedStatement.executeQuery();

                    resultSet.next();

                    System.out.println(resultSet.getString(1));

                    Thread.sleep(7000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }//for end
    }
}
