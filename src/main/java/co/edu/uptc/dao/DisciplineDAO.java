package co.edu.uptc.dao;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class DisciplineDAO {
    private final MongoCollection<Document> disciplinesCollection;

    public DisciplineDAO() {
        this.disciplinesCollection = ConnectionBaseData.getInstance().getDisciplineCollection();
    }
}
