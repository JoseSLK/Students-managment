package co.edu.uptc.model;

import java.util.ArrayList;

public class Discipline {
    private String name;
    private ArrayList<Integer> participants;
    private int id;

    public Discipline(String name, int id, ArrayList<Integer> participants) {
        this.name = name;
        this.participants = participants;
        this.id = id;
        this.participants = new ArrayList<>();
    }

    public Discipline() {
    }

    public ArrayList<Integer> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Integer> participants) {
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
