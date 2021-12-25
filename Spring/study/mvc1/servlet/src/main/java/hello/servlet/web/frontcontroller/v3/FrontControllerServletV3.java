package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// /front-controller/v2/members/new-form
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request); // 맨아래 코드에서 createMap 메서드를 뽑아낸다
        ModelView mv = controller.process(paramMap); // 위 코드에서 paramMap 을 넘겨받아야한다. //mv 에서는 논리이름만 제공가능

        String viewName = mv.getViewName(); //논리 이름만 조회된다.(mv.getViewName)

        MyView view = viewResolver(viewName); // 아래 viewResolver 메서도 활용

        view.render(mv.getModel(), request, response); //ModelView 에 있는 model 을 렌더에 같이 넘겨줘야한다.(MyView 에 작성)

    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp"); //논리이름(viewName)을 물리이름으로 변경해준다.
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator() // request에서 getParameterNames를 통해 parameter 을 전부 꺼내준 것을 paramMap에 넣어준다.
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName))); // key : paramName value : request.getParameter(paramName)
        return paramMap;
    }
}
