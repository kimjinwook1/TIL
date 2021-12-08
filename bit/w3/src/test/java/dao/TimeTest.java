package dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zerock.w3.dao.ConnectionUtil;

import java.sql.Connection;

@Log4j2
public class TimeTest {

    @Test
    void testNow() throws Exception {

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        log.info(connection);
        Assertions.assertNotNull(connection);

    }
}
