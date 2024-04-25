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

@WebServlet(name="todoModifyController", urlPatterns = "/todo/modify")
public class TodoModifyController extends HttpServlet {
    TodoService todoService = TodoService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try{
            TodoDTO todoDTO = todoService.selectOne(Long.parseLong(req.getParameter("tno")));
            req.setAttribute("todoDTO", todoDTO);
        }catch (Exception e){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/todo/modify.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TodoDTO todoDTO = TodoDTO.builder()
                        .tno(Long.parseLong(req.getParameter("tno")))
                        .title(req.getParameter("title"))
                        .dueDate(LocalDate.parse(req.getParameter("dueDate")))
                        .finished(Boolean.parseBoolean(req.getParameter("finished")))
                        .build();
        try{
            todoService.update(todoDTO);
        }catch (Exception e){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,"update 실패");
            e.printStackTrace();
        }
        resp.sendRedirect("/todo/list");
    }
}
