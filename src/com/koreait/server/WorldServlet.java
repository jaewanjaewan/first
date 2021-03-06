package com.koreait.server;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/world")
public class WorldServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String addr = req.getParameter("addr");
        String phone = req.getParameter("phone");
        String gender = req.getParameter("gender");
        System.out.println("addr : " + addr);
        System.out.println("phone : " + phone);
        System.out.println("gender : " + gender);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String data2 = Utils.getJson(req);
        TestVO vo = gson.fromJson(data2, TestVO.class);
        System.out.println(vo.getName());
        System.out.println(vo.getAge());
        System.out.println(data2);
    }
}
