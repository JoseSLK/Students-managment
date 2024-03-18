package co.edu.uptc.servlets;


import co.edu.uptc.controller.GeneralController;
import co.edu.uptc.dao.DisciplineDAO;
import co.edu.uptc.dao.EventDAO;
import co.edu.uptc.dao.StudentsDAO;
import co.edu.uptc.model.Discipline;
import co.edu.uptc.model.Event;
import co.edu.uptc.model.Student;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.client.MongoCursor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;

import java.io.BufferedReader;
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
            case "event-to-student":
                int idStudent = Integer.parseInt(request.getParameter("idStudent"));
                int idEvent = Integer.parseInt(request.getParameter("idEvent"));
                Student student1 = generalController.addEventToStudent(idEvent,idStudent);
                if(student1 != null){
                    response.getWriter().write("Se ha agregado un vento a " + student1.getName());
                }else response.getWriter().write("");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        Gson gson = new Gson();
        String type = request.getParameter("action");
        switch (type){
            case "add_student":
                BufferedReader bfr = request.getReader();
                Student student = gson.fromJson(bfr, Student.class);
                if(generalController.addStudent(student.getName(), student.getAge(), student.getDiscipline(), student.getId()) != null){
                    response.getWriter().write(gson.toJson(student));
                }else{
                    response.getWriter().write("");
                }
                break;
            case "add_event":
                BufferedReader ev = request.getReader();
                Event event = gson.fromJson(ev, Event.class);
                if(generalController.addEvent(event.getDisciplineId(), event.getLocation(), event.getDate(), event.getName(), event.getId()) != null){
                    response.getWriter().write(gson.toJson(event));
                } else {
                    response.getWriter().write("");
                }
                break;
            case "add_position_student":
                BufferedReader reader = request.getReader();
                StringBuilder requestBody = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    requestBody.append(line);
                }
                reader.close();
                JsonObject jsonObject = gson.fromJson(requestBody.toString(), JsonObject.class);
                int eventId = jsonObject.get("eventId").getAsInt();
                int studentId = jsonObject.get("studentId").getAsInt();
                Event event1 = generalController.addPositionToEvent(eventId, studentId);
                if(event1 != null){
                    response.getWriter().write(event1.getName()+": jugador agregado correctamente");
                }else response.getWriter().write("");
                break;
            case "add_discipline":
                BufferedReader dis = request.getReader();
                Discipline discipline = gson.fromJson(dis, Discipline.class);
                if(disciplineDAO.addDiscipline(discipline.getName(), discipline.getId()) != null){
                    response.getWriter().write(gson.toJson(discipline));
                }else response.getWriter().write("");
                break;

        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        resp.setContentType("text/plain");
        String type = req.getParameter("action");

        switch (type){
            case "delete_student":
                int id = Integer.parseInt(req.getParameter("id"));
                if(studentsDAO.findStudentById(id) != null){
                    resp.getWriter().write(gson.toJson(studentsDAO.deleteStudent(id)));
                }else resp.getWriter().write("");
                break;
            case "delete_event":
                int idEvent = Integer.parseInt(req.getParameter("id"));
                if (eventDAO.findEventById(idEvent) != null){
                    resp.getWriter().write(gson.toJson(eventDAO.deleteEvent(idEvent)));
                }else resp.getWriter().write("");
                break;
            case "delete_discipline":
                int idDiscipline = Integer.parseInt(req.getParameter("id"));
                if(disciplineDAO.findDisciplineById(idDiscipline) != null){
                    resp.getWriter().write(gson.toJson(disciplineDAO.deleteDiscipline(idDiscipline)));
                }else resp.getWriter().write("");
                break;
        }
    }
}

