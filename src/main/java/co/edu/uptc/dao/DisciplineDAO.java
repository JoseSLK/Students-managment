package co.edu.uptc.dao;

import co.edu.uptc.model.Discipline;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class DisciplineDAO {
    private final MongoCollection<Document> disciplinesCollection;

    public MongoCollection<Document> getDisciplinesCollection() {
        return disciplinesCollection;
    }

    public DisciplineDAO() {
        this.disciplinesCollection = ConnectionBaseData.getInstance().getDisciplineCollection();
    }

    public Discipline addDiscipline(String name, int id){
        if(findDisciplineById(id) == null){
            Discipline discipline = new Discipline(name, id, new ArrayList<>());
            Document doc = new Document("name", discipline.getName())
                    .append("_id", discipline.getId())
                    .append("participants", discipline.getParticipants());
            disciplinesCollection.insertOne(doc);
            return discipline;
        }return null;
    }
    public Discipline deleteDiscipline(int idDiscipline){
        Discipline disciplineToDelete = findDisciplineById(idDiscipline);
        if( disciplineToDelete != null ){
            Document query = new Document("_id", idDiscipline);
            disciplinesCollection.deleteOne(query);
        }
        return disciplineToDelete;
    }
    public Discipline modifyDiscipline(String name, int id){
        Discipline discipline = findDisciplineById(id);

        if(discipline != null){

            discipline.setName(name);

            Document query = new Document("_id", id);
            Document updateQuery = new Document("$set", new Document("name", name));
            UpdateResult result = disciplinesCollection.updateOne(query, updateQuery);

            if (result.getModifiedCount() > 0){
                return discipline;
            }else return null;
        }else return null;
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
    public Discipline addParticipantToDiscipline(int disciplineId, int participantId) {
        Discipline discipline = findDisciplineById(disciplineId);

        if (discipline != null) {
            ArrayList<Integer> participants = discipline.getParticipants();

            if (!participants.contains(participantId)) {
                participants.add(participantId);

                discipline.setParticipants(participants);

                Discipline updatedDiscipline = modifyDiscipline(discipline.getName(), disciplineId);

                if (updatedDiscipline != null) {
                    return updatedDiscipline;
                } else return null;
            }else return null;
        }
        return null;
    }

    public ArrayList<Discipline> getDisciplines(){
        ArrayList<Discipline> disciplines = new ArrayList<>();
        MongoCursor<Document> docDi = getDisciplinesCollection().find().iterator();
        try {
            while (docDi.hasNext()){
                Document doc = docDi.next();
                ArrayList<Integer> participantsList = (ArrayList<Integer>) doc.get("participants");
                Discipline discipline = new Discipline(doc.getString("name"), doc.getInteger("_id"), participantsList);
                disciplines.add(discipline);
            }
        } finally {
            docDi.close();
        }
        return disciplines;
    }

}
