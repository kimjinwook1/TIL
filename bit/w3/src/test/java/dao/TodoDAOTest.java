package dao;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.zerock.w3.dao.TodoDAO;

@Log4j2
class TodoDAOTest {

    @Test
    void testList() throws Exception {

        TodoDAO.INSTANCE.getAll().forEach(todoVO -> log.info(todoVO));
    }
}