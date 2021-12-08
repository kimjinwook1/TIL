package org.zerock.w3.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.zerock.w3.domain.TodoVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public enum TodoDAO {

    INSTANCE;

    public List<TodoVO> getAll() throws Exception {

        List<TodoVO> list = new ArrayList<>();

        String sql = "select `tno`,\n" +
                "       `title`,\n" +
                "       `writer`,\n" +
                "       `dueDate`,\n" +
                "       `finished`,\n" +
                "       `regDate`,\n" +
                "       `updateDate`\n" +
                "from tbl_todo\n" +
                "order by abs(datediff(dueDate, now())), tno desc";

        //tno, title, writer, dueDate, finished,
        //regDate, updateDate
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            TodoVO vo = TodoVO.builder()
                    .tno(resultSet.getLong(1))
                    .title(resultSet.getString(2))
                    .writer(resultSet.getString(3))
                    .dueDate(LocalDate.parse(resultSet.getString(4)))
                    .finished(resultSet.getBoolean(5))
                    .regDate(resultSet.getDate(6))
                    .updateDate(resultSet.getDate(7))
                    .build();

            list.add(vo);
        }

        return list;

    }
}
