package co.edu.uptc.servlets;


import co.edu.uptc.controller.GeneralController;
import co.edu.uptc.dao.DisciplineDAO;
import co.edu.uptc.dao.EventDAO;
import co.edu.uptc.dao.StudentsDAO;
import co.edu.uptc.model.Student;
import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletMain", value = "/servlet_main")
public class ServletMain extends HttpServlet {
    private DisciplineDAO disciplineDAO;
    private EventDAO eventDAO;
    private StudentsDAO studentsDAO;
    private GeneralController generalController;

    @Override
    public void init() throws ServletException {
        super.init();
        this.disciplineDAO = new DisciplineDAO();
        this.eventDAO = new EventDAO();
        this.studentsDAO = new StudentsDAO();
        generalController = new GeneralController(disciplineDAO, eventDAO, studentsDAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        Gson gson = new Gson();
        String type = request.getParameter("action");
        switch (type){
            case "list_student":
                response.getWriter().write(gson.toJson(studentsDAO.getListStudents()));
                break;
            case "list_events":
                response.getWriter().write(gson.toJson(eventDAO.getEvents()));
                break;
            case "list_disciplines":
                response.getWriter().write(gson.toJson(disciplineDAO.getDisciplines()));
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implement your servlet logic here
    }
}
