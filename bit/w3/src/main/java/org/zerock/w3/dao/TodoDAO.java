package org.zerock.w3.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.zerock.w3.domain.TodoVO;
import org.zerock.w3.util.DateUtil;
import org.zerock.w3.util.StringUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public enum TodoDAO {

    INSTANCE;

    public List<TodoVO> getAll() throws Exception {

        List<TodoVO> list = null;

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
        list = new ArrayList<>();

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

    public void save(TodoVO vo) throws Exception {

        String sql = "insert into tbl_todo (`title`, `writer`, `dueDate`) VALUES (?,?,?)";
        //title, writer, dueDate

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, vo.getTitle());
        preparedStatement.setString(2, vo.getWriter());
        preparedStatement.setDate(3, Date.valueOf(vo.getDueDate()));

        preparedStatement.executeUpdate();

    }

    //강사님 코드
    public void insert(TodoVO vo) throws Exception {

        String sql = "insert into tbl_todo (`title`, `writer`, `dueDate`) VALUES (?,?,?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, vo.getTitle());
        preparedStatement.setString(2, vo.getWriter());
        preparedStatement.setString(3, DateUtil.getStr(vo.getDueDate()));

        int count = preparedStatement.executeUpdate();

        log.info("Count: " + count);
    }

    public TodoVO selectOne(Long tno) throws Exception {
        TodoVO todoVO = null;

        String selet = " select " +
                "`tno`,`title`,`writer`,`dueDate`,`finished`,`regDate`, `updateDate` " +
                "from tbl_todo where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(selet);
        preparedStatement.setLong(1, tno);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        //tno, title, writer, dueDate, finished, regDate, updateDate

        int idx = 1;

        todoVO = TodoVO.builder()
                .tno(resultSet.getLong(idx++))
                .title(resultSet.getString(idx++))
                .writer(resultSet.getString(idx++))
                .dueDate(StringUtil.parseLocalDate(resultSet.getString(idx++)))
                .finished(resultSet.getBoolean(idx++))

                .regDate(resultSet.getDate(idx++))
                .updateDate(resultSet.getDate(idx++))
                .build();

        return todoVO;
    }

    public void deleteOne(Long tno) throws Exception {

        String sql = "delete from tbl_todo where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, tno);
        int count = preparedStatement.executeUpdate();

        log.info("Count: " + count);
    }

    public void modify(TodoVO todo) throws Exception {

        String sql = "UPDATE `tbl_todo`\n" +
                "SET `title` = ?, dueDate = ?, `finished` = ?\n" +
                "WHERE `tno` = ?";

        //title, dueDate, finished, tno
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, todo.getTitle());
        preparedStatement.setString(2, DateUtil.getStr(todo.getDueDate()));
        preparedStatement.setBoolean(3, todo.isFinished());
        preparedStatement.setLong(4, todo.getTno());

        preparedStatement.executeUpdate();
    }

}
