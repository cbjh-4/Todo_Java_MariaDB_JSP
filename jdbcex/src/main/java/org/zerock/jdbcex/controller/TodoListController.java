package org.zerock.jdbcex.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.service.TodoService;

import java.io.IOException;
import java.util.List;

@WebServlet(name="todoListController", urlPatterns = "/todo/list")
public class TodoListController extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try{
            List<TodoDTO> dtoList = todoService.listAll();
            req.setAttribute("dtoList", dtoList);
            req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
