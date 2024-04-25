package org.zerock.jdbcex.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.service.TodoService;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name="todoRegisterController", urlPatterns = "/todo/register")
public class TodoRegisterController extends HttpServlet {
    TodoService todoService = TodoService.INSTANCE;
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TodoDTO todoDTO = TodoDTO.builder()
                .title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("dueDate")))
                .finished(Boolean.parseBoolean(req.getParameter("finished")))
                .build();
        try{
            todoService.register(todoDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        resp.sendRedirect("/todo/list");
    }
}
