package org.zerock.w3.servletmvc.dao;

import lombok.Cleanup;
import org.zerock.w3.servletmvc.domain.MemberVO;
import org.zerock.w3.servletmvc.dto.MemberDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public enum MemberDAO {
    INSTANCE;

    public MemberVO selectOne(String userid, String userPw) throws Exception {

        MemberVO memberVO = null;

        String sql = "select `userid`, `userpw`, `username`, `uno` from `tbl_member` where `userid` = ? and `userPw` = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userid);
        preparedStatement.setString(2, userPw);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        int idx = 1;

        resultSet.next();
        memberVO = MemberVO.builder()
                .userid(resultSet.getString(idx++))
                .userpw(resultSet.getString(idx++))
                .username(resultSet.getString(idx++))
                .uno(resultSet.getInt(idx++))
                .build();

        return memberVO;

    }

    public void updateCookie(String userid, String uuid, Date expTime) throws Exception {

        String sql = "update `tbl_member` set `uuid`= ?, `exptime`= ? where `userid` =?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, uuid);
        preparedStatement.setDate(2, expTime);
        preparedStatement.setString(3, userid);

        preparedStatement.executeUpdate();
    }

    public MemberVO finByUUID(String uuid) throws Exception {
        MemberVO memberVO = null;

        String sql = "select `userid`, `userpw`, `username` from `tbl_member` where `uuid` = ? and `exptime` > now()";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, uuid);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        memberVO = MemberVO.builder()
                .uuid(resultSet.getString(1))
                .expTime(resultSet.getDate(2))
                .userid(resultSet.getString(3))
                .build();

        return memberVO;
    }

    public MemberVO SelectByUserID(String userid) throws Exception {

        MemberVO memberVO = null;

        String sql = "select `userid`, `userpw`, `username`, `uno` from `tbl_member` where `userid` = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userid);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            memberVO = MemberVO.builder()
                    .userid(resultSet.getString(1))
                    .userpw(resultSet.getString(2))
                    .username(resultSet.getString(3))
                    .uno(resultSet.getInt(4))
                    .build();
        }

        return memberVO;
    }

    public void register(MemberDTO memberDTO) throws Exception {

        String sql = "insert into tbl_member (`userid`, `userpw`, `username`) VALUES (?,?,?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, memberDTO.getUserid());
        preparedStatement.setString(2, memberDTO.getUserpw());
        preparedStatement.setString(3, memberDTO.getUserpw());

        preparedStatement.executeUpdate();

    }

    public void updateMember(MemberDTO memberDTO) throws Exception {

        String sql = "update `tbl_member` set `username` = ?, `userpw` = ? where uno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, memberDTO.getUsername());
        preparedStatement.setString(2, memberDTO.getUserpw());
        preparedStatement.setInt(3, memberDTO.getUno());

        preparedStatement.executeUpdate();
    }

    public MemberVO selectOneByUno(int uno) throws Exception {

        MemberVO memberVO = null;

        String sql = "select `userid`, `userpw`, `username`, `uno` from `tbl_member` where `uno` = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, uno);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        memberVO = memberVO.builder()
                .userid(resultSet.getString("userid"))
                .userpw(resultSet.getString("userpw"))
                .username(resultSet.getString("username"))
                .uno(resultSet.getInt("uno"))
                .build();

        return memberVO;
    }

    public void remove(int uno) throws Exception {

        String sql2 = "delete from `tbl_member` where `uno` = ?";
        @Cleanup Connection connection2 = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement2 = connection2.prepareStatement(sql2);
        preparedStatement2.setInt(1, uno);
        preparedStatement2.executeUpdate();

    }
}
