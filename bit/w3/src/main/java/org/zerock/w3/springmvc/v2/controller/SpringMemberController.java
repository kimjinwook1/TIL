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
            @RequestParam("userid") String userid,
            @RequestParam("userpw") String userPw,
            @RequestParam(value = "rememberme", required = false) boolean rememberme,
            @CookieValue(value = "remember-me", required = false) Cookie rememberCookie,
            HttpServletResponse response,
            Model model) {

        try {
            MemberDTO memberDTO = MemberService.INSTANCE.get(userid, userPw);
            if (memberDTO == null) {
                return "redirect:/member/login";
            }
            model.addAttribute("userInfo", memberDTO);
            if (rememberme) {

                String uuid = UUID.randomUUID().toString();

                //1week
                long endTime = System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7);

                rememberCookie.setPath("/"); //setPath("/") 모든 경로에 쿠키를 저장한다.
                rememberCookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(rememberCookie);

                MemberService.INSTANCE.setCookieData(userid, uuid, new Date(endTime));

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
    public String getModify(@RequestParam("uno") int uno, Model model) {

        try {
            MemberDTO memberDTO = MemberService.INSTANCE.getByUno(uno);
            model.addAttribute("memberDTO", memberDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/member/membermodify";
    }

    @PostMapping("/modify")
    public String postModify(
            @RequestParam("userpw") String userpw,
            @RequestParam("username") String username,
            @RequestParam("uno") int uno,
            Model model
    ) throws Exception {

        MemberDTO memberDTO = MemberDTO.builder()
                .userpw(userpw)
                .username(username)
                .uno(uno)
                .build();

        try {
            TodoService.INSTANCE.updateWriter(username, uno);
            MemberService.INSTANCE.update(memberDTO);
            model.addAttribute("userInfo", memberDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/member/read";
    }

    @GetMapping("/remove")
    public String getRemove(@RequestParam("uno") int uno, Model model) {

        try {
            MemberDTO memberDTO = MemberService.INSTANCE.getByUno(uno);
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
    public String postSignup(@RequestParam("userid") String userid,
                         @RequestParam("userpw") String userpw,
                         @RequestParam("checkpw") String checkpw,
                         @RequestParam("username") String username) {

        MemberDTO memberDTO = null;

        try {
            boolean idCheck = SignUpService.INSTANCE.checkDuplicate(userid);
            log.info("idCheck..........." + idCheck);
            if (idCheck) {
                return "redirect:/member/signup";
            }

            if (userpw.equals(checkpw)) {
                memberDTO = MemberDTO.builder()
                        .userid(userid)
                        .userpw(userpw)
                        .username(username)
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
