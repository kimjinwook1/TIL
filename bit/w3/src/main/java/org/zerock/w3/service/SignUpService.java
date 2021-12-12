package org.zerock.w3.service;

import org.zerock.w3.dao.MemberDAO;
import org.zerock.w3.domain.MemberVO;
import org.zerock.w3.dto.MemberDTO;

public enum SignUpService {

    INSTANCE;

    public boolean checkDuplicate(String userid) throws Exception {

        MemberVO memberVO = MemberDAO.INSTANCE.SelectByUserID(userid);

        if (memberVO == null) {
            return false;
        }
        return true;
    }

    public void register(MemberDTO memberDTO) throws Exception{

        MemberDAO.INSTANCE.register(memberDTO);
    }
}
