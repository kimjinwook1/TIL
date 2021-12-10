package org.zerock.w3.dao;

import lombok.Cleanup;
import org.zerock.w3.domain.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public enum MemberDAO {
    INSTANCE;

    public MemberVO selectOne(String userid) throws Exception {

        MemberVO memberVO = null;

        String sql = "select `userid`, `userpw`, `username` from `tbl_member` where `userid` = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userid);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        memberVO = MemberVO.builder()
                .userid(resultSet.getString(1))
                .userpw(resultSet.getString(2))
                .username(resultSet.getString(3))
                .build();

        return memberVO;

    }

}
