package co.edu.uptc.dao;

import co.edu.uptc.model.Student;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class StudentsDAO {
    private final MongoCollection<Document> studentCollection;
    public StudentsDAO() {
        this.studentCollection = ConnectionBaseData.getInstance().getStudentCollection();
    }

    public MongoCollection<Document> getStudentCollection() {
        return studentCollection;
    }
    public Student findStudentById(int idStudent){
        Document query = new Document("_id",idStudent);
        Document doc = studentCollection.find(query).first();

        if (doc != null) {
            String name = doc.getString("name");
            int age = doc.getInteger("age");
            int discipline = doc.getInteger("discipline");
            List<Integer> eventsId = (List<Integer>) doc.get("events");
            ArrayList<Integer> events = new ArrayList<>(eventsId);

            return new Student(name, age, discipline, idStudent);
        }else return null;
    }
    public Student addStudent(String name, int age, int discipline, int id){
        if(findStudentById(id) == null){
            Student student = new Student(name, age, discipline, id);
            Document doc = new Document("_id", student.getId())
                    .append("name", student.getName())
                    .append("age", student.getAge()).append("discipline", student.getDiscipline())
                    .append("events", new ArrayList<>());
            studentCollection.insertOne(doc);
            return student;
        }else return null;
    }
    public Student deleteStudent(int id){
        Student student = findStudentById(id);
        if(student != null){
            Document query = new Document("_id", id);
            studentCollection.deleteOne(query);
            return student;
        }else return null;
    }
    public Student modifyStudent(String name, int age, int discipline, int id){
        Student student = findStudentById(id);
        if(student != null){
            student.setAge(age);
            student.setName(name);
            student.setDiscipline(discipline);

            Document query = new Document("_id", id);
            Document updateQuery = new Document("$set", new Document("name", name)
                    .append("age", student.getAge())
                    .append("discipline", student.getDiscipline()));
            UpdateResult result = studentCollection.updateOne(query, updateQuery);
            if (result.getModifiedCount() > 0){
                return student;
            }else return null;
        }else return null;
    }

}
