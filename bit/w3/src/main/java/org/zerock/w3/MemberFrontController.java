package org.zerock.w3;

import javax.servlet.annotation.WebServlet;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "memberFrontController", urlPatterns = "/member/*")
public class MemberFrontController extends HelloServlet {

    private Map<String, Controller> controllerMap = new HashMap<>();

    public MemberFrontController() {
//        controllerMap.put("/member/remove/*", new MemberRemoveController());
    }
}
