package org.zerock.w3.servletmvc.service;

import lombok.extern.log4j.Log4j2;
import org.zerock.w3.servletmvc.dao.MemberDAO;
import org.zerock.w3.servletmvc.domain.MemberVO;
import org.zerock.w3.servletmvc.dto.MemberDTO;
import org.zerock.w3.util.MapperUtil;

import java.sql.Date;

@Log4j2
public enum MemberService {

    INSTANCE;

    public MemberDTO get(String userid, String userPw) throws Exception {

        MemberVO vo = MemberDAO.INSTANCE.selectOne(userid, userPw);
        MemberDTO memberDTO = MapperUtil.INSTANCE.get().map(vo, MemberDTO.class);

        return memberDTO;
    }

    public void setCookieData(String userid, String uuid, Date expTime) throws Exception {

        MemberDAO.INSTANCE.updateCookie(userid, uuid, expTime);
    }

    public MemberDTO getByUUID(String uuid) throws Exception {

        MemberVO vo = MemberDAO.INSTANCE.finByUUID(uuid);
        MemberDTO memberDTO = MapperUtil.INSTANCE.get().map(vo, MemberDTO.class);

        return memberDTO;
    }

    public void update(MemberDTO memberDTO) throws Exception {

        MemberDAO.INSTANCE.updateMember(memberDTO);
    }

    public MemberDTO getByUno(int uno) throws Exception {

        MemberVO memberVO = MemberDAO.INSTANCE.selectOneByUno(uno);
        MemberDTO memberDTO = MapperUtil.INSTANCE.get().map(memberVO, MemberDTO.class);

        return memberDTO;
    }

    public void remove(int uno) throws Exception{

        MemberDAO.INSTANCE.remove(uno);

    }
}
