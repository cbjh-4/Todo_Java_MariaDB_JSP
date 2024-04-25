package org.zerock.jdbcex.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.zerock.jdbcex.service.TodoService;

import java.io.IOException;

@WebServlet(name = "todoDeleteController", urlPatterns = "/todo/delete")
public class TodoDeleteController extends HttpServlet {
    TodoService todoService = TodoService.INSTANCE;
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            todoService.delete(Long.parseLong(req.getParameter("tno")));
        }catch (Exception e){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "삭제가 실패했습니다.");
            e.printStackTrace();
        }
        resp.sendRedirect("/todo/list");
    }
}
