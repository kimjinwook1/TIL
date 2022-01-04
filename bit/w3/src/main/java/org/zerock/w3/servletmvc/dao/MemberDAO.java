package org.zerock.w3.servletmvc.dao;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.zerock.w3.servletmvc.domain.MemberVO;
import org.zerock.w3.servletmvc.dto.MemberDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Slf4j
public enum MemberDAO {
    INSTANCE;

    public MemberVO selectOne(String userId, String userPw) throws Exception {

        MemberVO memberVO = null;

        String sql = "select `userid`, `userpw`, `username`, `uno` from `tbl_member` where `userId` = ? and `userPw` = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userId);
        preparedStatement.setString(2, userPw);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        int idx = 1;

        if (resultSet.next()) {
            memberVO = MemberVO.builder()
                    .userId(resultSet.getString(idx++))
                    .userPw(resultSet.getString(idx++))
                    .username(resultSet.getString(idx++))
                    .uno(resultSet.getInt(idx))
                    .build();
        }
        log.info("memberVo={}", memberVO);
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
                .expTime(resultSet.getDate(1))
                .userId(resultSet.getString(2))
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
                    .userId(resultSet.getString(1))
                    .userPw(resultSet.getString(2))
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

        preparedStatement.setString(1, memberDTO.getUserId());
        preparedStatement.setString(2, memberDTO.getUserPw());
        preparedStatement.setString(3, memberDTO.getUserPw());

        preparedStatement.executeUpdate();

    }

    public void updateMember(MemberDTO memberDTO) throws Exception {

        String sql = "update `tbl_member` set `username` = ?, `userpw` = ? where uno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, memberDTO.getUsername());
        preparedStatement.setString(2, memberDTO.getUserPw());
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
                .userId(resultSet.getString("userId"))
                .userPw(resultSet.getString("userPw"))
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
