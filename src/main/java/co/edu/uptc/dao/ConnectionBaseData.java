package co.edu.uptc.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.MongoDatabase;

public class ConnectionBaseData {
    private static final String CONNECTIONSTRING = "mongodb+srv://dataCorn:data.12354@datastudents.adaasru.mongodb.net/tu_base_de_datos?retryWrites=true&w=majority";
    private static final String DATABASENAME = "data";
    private static final String COLLECTIONSTUDENTS = "students";
    private static final String COLLECTIONEVENTS = "events";
    private static final String COLLECTIONDISCIPLINES = "disciplines";

    private static ConnectionBaseData instance;
    private final MongoCollection<Document> studentCollection;
    private final MongoCollection<Document> eventsCollection;
    private final MongoCollection<Document> disciplineCollection;

    public ConnectionBaseData() {
        MongoClient mongoClient = MongoClients.create(CONNECTIONSTRING);
        MongoDatabase database = mongoClient.getDatabase(DATABASENAME);
        this.studentCollection = database.getCollection(COLLECTIONSTUDENTS);
        this.disciplineCollection = database.getCollection(COLLECTIONDISCIPLINES);
        this.eventsCollection = database.getCollection(COLLECTIONEVENTS);
    }

    public static synchronized ConnectionBaseData getInstance() {
        if (instance == null) {
            instance = new ConnectionBaseData();
        }
        return instance;
    }

    public MongoCollection<Document> getStudentCollection() {
        return studentCollection;
    }

    public MongoCollection<Document> getEventsCollection() {
        return eventsCollection;
    }

    public MongoCollection<Document> getDisciplineCollection() {
        return disciplineCollection;
    }
}
