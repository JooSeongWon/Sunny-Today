package xyz.sunnytoday.controller;

import xyz.sunnytoday.common.config.AppConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/verification")
public class VerificationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("req.getParameter(\"join\") = " + req.getParameter("join"));
        System.out.println("AppConfig.getTemporaryMemberRepo().getMember(req.getParameter(\"join\")) = " + AppConfig.getTemporaryMemberRepo().getMember(req.getParameter("join")));

        resp.sendRedirect(req.getContextPath());
    }
}
