package co.edu.uptc.controller;

import co.edu.uptc.dao.DisciplineDAO;
import co.edu.uptc.dao.EventDAO;
import co.edu.uptc.dao.StudentsDAO;
import co.edu.uptc.model.Discipline;
import co.edu.uptc.model.Event;
import co.edu.uptc.model.Student;

import java.util.Date;

public class GeneralController {
    private DisciplineDAO disciplineDAO;
    private EventDAO eventDAO;
    private StudentsDAO studentsDAO;

    public GeneralController(DisciplineDAO disciplineDAO, EventDAO eventDAO, StudentsDAO studentsDAO) {
        this.disciplineDAO = disciplineDAO;
        this.eventDAO = eventDAO;
        this.studentsDAO = studentsDAO;
    }

    public Student addStudent(String name, int age, int discipline, int id){
        if( disciplineDAO.findDisciplineById(discipline) != null){
            studentsDAO.addStudent(name, age, discipline, id);
            return new Student(name, age, discipline, id);
        }else return null;
    }
//    public Discipline addDiscipline(String name, int id){
//        return disciplineDAO.addDiscipline(name,id);
//    }

    public Event addEvent(int disciplineId, String location, Date date, String name, int id) {
        Discipline discipline = disciplineDAO.findDisciplineById(disciplineId);
        if(discipline != null){
            return eventDAO.addEvent(disciplineId,location,date,name,id);
        }
        return null;
    }
    public Student addStudentToDiscipline(int idStudent, int disciplineId){
        if(studentsDAO.findStudentById(idStudent) != null){
            disciplineDAO.addParticipantToDiscipline(disciplineId, idStudent);
            return studentsDAO.findStudentById(idStudent);
        }
        return null;
    }
    public Student addEventToStudent(int eventId, int studentId){
        Event event = eventDAO.findEventById(eventId);
        if(event != null){
            Student st = studentsDAO.addEventToStudent(studentId, eventId);
            if (st != null){
                return st;
            }else return null;
        }
        return null;
    }

    public Event addPositionToEvent(int eventId, int positionId){
        if(studentsDAO.findStudentById(positionId) != null){
            return eventDAO.addPositionToEvent(eventId,positionId);
        }else return null;
    }




}
