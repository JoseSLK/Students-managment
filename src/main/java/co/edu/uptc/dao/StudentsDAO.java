package co.edu.uptc.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

public class StudentsDAO {
    private final MongoCollection<Document> studentCollection;
    public StudentsDAO() {
        this.studentCollection = ConnectionBaseData.getInstance().getStudentCollection();
    }

    public MongoCollection<Document> getStudentCollection() {
        return studentCollection;
    }
}
