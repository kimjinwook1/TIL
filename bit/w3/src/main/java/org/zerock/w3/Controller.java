package org.zerock.w3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {

    MyView doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    MyView doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;


}
