package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.Date;

public class Event {
    private int disciplineId;
    private ArrayList<Integer> positions;
    private String location;
    private Date date;
    private String name;
    private int id;

    public Event(int discipline, String location, Date date, String name, int id) {
        this.disciplineId = discipline;
        this.positions = new ArrayList<>();
        this.location = location;
        this.date = date;
        this.name = name;
        this.id = id;
    }

    public Event(int disciplineId, String location, Date date, String name, int id, ArrayList<Integer> positions) {
        this.disciplineId = disciplineId;
        this.positions = positions;
        this.location = location;
        this.date = date;
        this.name = name;
        this.id = id;
    }

    public int getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(int disciplineId) {
        this.disciplineId = disciplineId;
    }

    public ArrayList<Integer> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Integer> positions) {
        this.positions = positions;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
