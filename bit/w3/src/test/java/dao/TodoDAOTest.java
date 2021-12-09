package dao;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zerock.w3.dao.TodoDAO;
import org.zerock.w3.domain.TodoVO;

@Log4j2
class TodoDAOTest {

    @Test
    void testList() throws Exception {

        TodoDAO.INSTANCE.getAll().forEach(todoVO -> log.info(todoVO));
    }

    @Test
    void testRead() throws Exception {

        TodoVO todoVO = TodoDAO.INSTANCE.selectOne(3L);

        Assertions.assertNotNull(todoVO);

        log.info(todoVO);
    }
}