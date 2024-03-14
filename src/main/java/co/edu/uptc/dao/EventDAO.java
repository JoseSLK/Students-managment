package co.edu.uptc.dao;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class EventDAO {
    private final MongoCollection<Document> eventsCollection;

    public EventDAO() {
        this.eventsCollection = ConnectionBaseData.getInstance().getEventsCollection();
    }
}
