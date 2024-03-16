package co.edu.uptc.dao;

import co.edu.uptc.model.Discipline;
import co.edu.uptc.model.Student;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class DisciplineDAO {
    private final MongoCollection<Document> disciplinesCollection;

    public DisciplineDAO() {
        this.disciplinesCollection = ConnectionBaseData.getInstance().getDisciplineCollection();
    }

    public Discipline addDiscipline(String name, int id){
        Discipline discipline = new Discipline(name, id, new ArrayList<>());
        Document doc = new Document("name", discipline.getName())
                .append("_id", discipline.getId())
                .append("participants", discipline.getParticipants());
        disciplinesCollection.insertOne(doc);
        return discipline;
    }
    public Discipline deleteDiscipline(){
        return null;
    }
    public Discipline modifyDiscipline(){
        return null;
    }
    public Discipline findDisciplineById(int idDiscipline){
        Document query = new Document("_id", idDiscipline);
        Document doc = disciplinesCollection.find(query).first();

        if(doc != null){
            String name = doc.getString("name");
            int id = doc.getInteger("_id");
            List<Integer> participantIds = (List<Integer>) doc.get("participants");
            ArrayList<Integer> participants = new ArrayList<>(participantIds);

            return new Discipline(name, id, participants);
        }else return null;
    }
}
