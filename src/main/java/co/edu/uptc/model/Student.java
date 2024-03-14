package co.edu.uptc.model;

import java.util.ArrayList;

public class Student {
    private String name;
    private int age;
    private int discipline;
    private int id;
    private ArrayList<Event> events;

    public Student(String name, int age, int discipline, int id) {
        this.name = name;
        this.age = age;
        this.discipline = discipline;
        this.id = id;
        this.events = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDiscipline() {
        return discipline;
    }

    public void setDiscipline(int discipline) {
        this.discipline = discipline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
