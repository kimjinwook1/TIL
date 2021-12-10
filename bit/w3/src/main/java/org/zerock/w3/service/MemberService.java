package org.zerock.w3.service;

import lombok.extern.log4j.Log4j2;
import org.zerock.w3.dao.MemberDAO;
import org.zerock.w3.domain.MemberVO;
import org.zerock.w3.dto.MemberDTO;
import org.zerock.w3.util.MapperUtil;

@Log4j2
public enum MemberService {

    INSTANCE;

    public MemberDTO get(String userid) throws Exception {

        MemberVO vo = MemberDAO.INSTANCE.selectOne(userid);
        MemberDTO memberDTO = MapperUtil.INSTANCE.get().map(vo, MemberDTO.class);

        return memberDTO;
    }

}
