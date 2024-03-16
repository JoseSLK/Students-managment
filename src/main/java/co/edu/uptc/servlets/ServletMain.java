package co.edu.uptc.servlets;


import co.edu.uptc.dao.DisciplineDAO;
import co.edu.uptc.dao.EventDAO;
import co.edu.uptc.dao.StudentsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ServletMain", value = "/servlet_main")
public class ServletMain extends HttpServlet {
    private DisciplineDAO disciplineDAO;
    private EventDAO eventDAO;
    private StudentsDAO studentsDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.disciplineDAO = new DisciplineDAO();
        this.eventDAO = new EventDAO();
        this.studentsDAO = new StudentsDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implement your servlet logic here
    }
}
