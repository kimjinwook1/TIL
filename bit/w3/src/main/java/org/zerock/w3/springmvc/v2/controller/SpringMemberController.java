package org.zerock.w3.springmvc.v2.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.w3.servletmvc.dto.MemberDTO;
import org.zerock.w3.servletmvc.service.MemberService;
import org.zerock.w3.servletmvc.service.SignUpService;
import org.zerock.w3.servletmvc.service.TodoService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.UUID;

@Log4j2
@Controller
@SessionAttributes("userInfo")
@RequestMapping("/member")
public class SpringMemberController {

    @GetMapping("/login")
    public String getLogin() {
        log.info("login..............");
        return "member/login";
    }

    @PostMapping("/login")
    public String postLogin(
            @ModelAttribute MemberDTO memberDTO,
            @RequestParam(value = "rememberme", required = false) boolean rememberme,
            @CookieValue(value = "remember-me", required = false) Cookie rememberCookie,
            HttpServletResponse response,
            HttpServletRequest request,
            Model model) {

        try {
            memberDTO = MemberService.INSTANCE.get(memberDTO.getUserId(), memberDTO.getUserpw());
            log.info("memberDTO={}", memberDTO);
            if (memberDTO == null) {
                return "redirect:/member/login";
            }
            model.addAttribute("userInfo", memberDTO);
            if (rememberme) {
//                String uuid = UUID.randomUUID().toString();
                //1week
//                long endTime = System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7);
//                rememberCookie.setPath("/"); //setPath("/") 모든 경로에 쿠키를 저장한다.
//                rememberCookie.setMaxAge(60 * 60 * 24 * 7);
//                response.addCookie(rememberCookie);
//                MemberService.INSTANCE.setCookieData(memberDTO.getUserId(), uuid, new Date(endTime));
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(60);
                session.setAttribute("userInfo", memberDTO);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/todo/list";

    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {

        session.removeAttribute("userInfo");
        session.invalidate(); //세션이 무효화 되고 만약 로그인을 다시 하게 되면 새로운 세션을 발급해준다.

        return "redirect:/member/login";
    }

    @GetMapping("/read")
    public String read(@ModelAttribute("userInfo") MemberDTO memberDTOParameter,
                       Model model) {

        int uno = memberDTOParameter.getUno();

        try {
            MemberDTO memberDTO = MemberService.INSTANCE.getByUno(uno);
            model.addAttribute("memberDTO", memberDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/member/memberread";

    }

    @GetMapping("/modify")
    public String getModify(@ModelAttribute MemberDTO memberDTO, Model model) {

        try {
            memberDTO = MemberService.INSTANCE.getByUno(memberDTO.getUno());
            model.addAttribute("memberDTO", memberDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/member/membermodify";
    }

    @PostMapping("/modify")
    public String postModify(
            @ModelAttribute MemberDTO memberDTO, Model model) {

        memberDTO = MemberDTO.builder()
                .userpw(memberDTO.getUserpw())
                .username(memberDTO.getUsername())
                .uno(memberDTO.getUno())
                .build();

        try {
            TodoService.INSTANCE.updateWriter(memberDTO.getUsername(), memberDTO.getUno());
            MemberService.INSTANCE.update(memberDTO);
            model.addAttribute("userInfo", memberDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/member/read";
    }

    @GetMapping("/remove")
    public String getRemove(@ModelAttribute MemberDTO memberDTO, Model model) {

        try {
            memberDTO = MemberService.INSTANCE.getByUno(memberDTO.getUno());
            model.addAttribute("memberDTO", memberDTO);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "/member/memberremove";
    }

    @PostMapping("/remove")
    public String postRemove(@RequestParam("uno") int uno,
                             @RequestParam("checkpw") String checkpw) {

        MemberDTO memberDTO = null;
        try {
            memberDTO = MemberService.INSTANCE.getByUno(uno);

            if (checkpw.equals(memberDTO.getUserpw())) {
                TodoService.INSTANCE.removeByUno(uno);
                MemberService.INSTANCE.remove(uno);
                return "redirect:/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/member/read";

    }


    @GetMapping("/signup")
    public String getSignup() throws Exception {

        return "member/signup";

    }

    @PostMapping("/signup")
    public String postSignup(@ModelAttribute MemberDTO memberDTO,
                             @RequestParam("checkpw") String checkpw) {

        try {
            boolean idCheck = SignUpService.INSTANCE.checkDuplicate(memberDTO.getUserId());
            log.info("idCheck..........." + idCheck);
            if (idCheck) {
                return "redirect:/member/signup";
            }

            if (memberDTO.getUserpw().equals(checkpw)) {
                memberDTO = MemberDTO.builder()
                        .userId(memberDTO.getUserId())
                        .userpw(memberDTO.getUserpw())
                        .username(memberDTO.getUsername())
                        .build();

                SignUpService.INSTANCE.register(memberDTO);
                return "redirect:/member/login";

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/member/signup";
    }
}
