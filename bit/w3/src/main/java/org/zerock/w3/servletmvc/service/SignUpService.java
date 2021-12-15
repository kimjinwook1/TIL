package org.zerock.w3.servletmvc.service;

import org.zerock.w3.servletmvc.dao.MemberDAO;
import org.zerock.w3.servletmvc.domain.MemberVO;
import org.zerock.w3.servletmvc.dto.MemberDTO;

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
