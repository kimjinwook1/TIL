package dao;

import com.example.d1_1.dao.StoreDAO;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOTests {

    //의존성/ 협력객체들

    private StoreDAO storeDAO;

    @BeforeEach
    private void ready() {
        System.out.println("ready..............");
        storeDAO = new StoreDAO();
    }

    @Test
    void test333() throws Exception {

        for (int i = 0; i < 300; i++) {

            System.out.println(storeDAO.calcGap3("2020-12-01", "2021-12-01"));

        }
    }

    @Test
    void test1() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
//            new Thread(() -> {
            try {
                String url = "jdbc:mysql://192.168.0.189:3306/webdb";

                Connection connection = DriverManager.getConnection(url, "webuser", "webuser");

                System.out.println(connection);

                PreparedStatement preparedStatement = connection.prepareStatement("select now()");
                ResultSet resultSet = preparedStatement.executeQuery();

                resultSet.next();

                System.out.println(resultSet.getString(1));

                Thread.sleep(3000);

            } catch (Exception e) {
                e.printStackTrace();
            }
//            }).start();
            long end = System.currentTimeMillis();

            System.out.println("TOTAL: " + (end - start));

        }//for end
    }

    @Test
    void test2() throws Exception {

        //Hikari -> Connection Pool과 DB를 관리해준다.
        //HikariCP는 미리 정해놓은 만큼의 커넥션을 Pool에 담아 놓는다.
        //요청이 들어오면 Thread가 커넥션을 요청하고 Hikari는 Pool내에 있는 커넥션을 연결해준다.
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://192.168.0.189:3306/webdb");
        config.setUsername("webuser");
        config.setPassword("webuser");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(config);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 300; i++) {

            Connection connection = ds.getConnection();
            System.out.println(connection);

            connection.close();

        }//for end

        long end = System.currentTimeMillis();
        System.out.println("Total: " + (end - start));

    }
}
