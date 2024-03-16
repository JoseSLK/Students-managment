package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.Date;

public class Event {
    private int discipline;
    private ArrayList<Integer> position;
    private String location;
    private Date date;
    private String name;
    private int id;

    public Event(int discipline, String location, Date date, String name, int id) {
        this.discipline = discipline;
        this.position = new ArrayList<>();
        this.location = location;
        this.date = date;
        this.name = name;
        this.id = id;
    }

    public int getDiscipline() {
        return discipline;
    }

    public void setDiscipline(int discipline) {
        this.discipline = discipline;
    }

    public ArrayList<Integer> getPosition() {
        return position;
    }

    public void setPosition(ArrayList<Integer> position) {
        this.position = position;
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
