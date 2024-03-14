package co.edu.uptc.model;

import java.util.ArrayList;

public class Discipline {
    private String name;
    private ArrayList<Student> participants;
    private int id;

    public Discipline(String name, int id) {
        this.name = name;
        this.id = id;
        this.participants = new ArrayList<>();
    }

    public ArrayList<Student> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Student> participants) {
        this.participants = participants;
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
