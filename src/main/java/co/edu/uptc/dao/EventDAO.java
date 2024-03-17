package co.edu.uptc.dao;


import co.edu.uptc.model.Event;
import co.edu.uptc.model.Student;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDAO {
    private final MongoCollection<Document> eventsCollection;

    public EventDAO() {
        this.eventsCollection = ConnectionBaseData.getInstance().getEventsCollection();
    }

    public MongoCollection<Document> getEventsCollection() {
        return eventsCollection;
    }

    public Event addEvent(int discipline, String location, Date date, String name, int id) {
        Event event = new Event(discipline, location, date, name, id);
        Document doc = new Document("discipline", event.getDiscipline())
                .append("_id", event.getId())
                .append("position", event.getPosition())
                .append("location", event.getLocation())
                .append("date", event.getDate())
                .append("name", event.getName());
        eventsCollection.insertOne(doc);
        return event;
    }

    public Event deleteEvent(int eventId) {
        Event eventToDelete = findEventById(eventId);
        if (eventToDelete != null) {
            Document query = new Document("_id", eventId);
            eventsCollection.deleteOne(query);
        }
        return eventToDelete;
    }

    public Event modifyEvent(int eventId, String location, Date date, String name) {
        Event event = findEventById(eventId);

        if (event != null) {
            event.setLocation(location);
            event.setDate(date);
            event.setName(name);

            Document query = new Document("_id", eventId);
            Document updateQuery = new Document("$set", new Document("location", location)
                    .append("date", date)
                    .append("name", name));
            UpdateResult result = eventsCollection.updateOne(query, updateQuery);

            if (result.getModifiedCount() > 0) {
                return event;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Event findEventById(int eventId) {
        Document query = new Document("_id", eventId);
        Document doc = eventsCollection.find(query).first();

        if (doc != null) {
            int discipline = doc.getInteger("disciplineId");
            ArrayList<Integer> positionIds = (ArrayList<Integer>) doc.get("positions");
            String location = doc.getString("location");
            Date date = doc.getDate("date");
            String name = doc.getString("name");

            return new Event(discipline, location, date, name, eventId);
        } else {
            return null;
        }
    }

    public Event addPositionToEvent(int eventId, int positionId) {
        Event event = findEventById(eventId);

        if (event != null) {
            ArrayList<Integer> positions = event.getPosition();

            if (!positions.contains(positionId)) {
                positions.add(positionId);

                Document query = new Document("_id", eventId);
                Document updateQuery = new Document("$set", new Document("position", positions));
                UpdateResult result = eventsCollection.updateOne(query, updateQuery);

                if (result.getModifiedCount() > 0) {
                    return findEventById(eventId);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public ArrayList<Event> getEvents(){
        ArrayList<Event> studentsDoc = new ArrayList<>();
        MongoCursor<Document> cursor = getEventsCollection().find().iterator();
        try {
            while (cursor.hasNext()){
                Document doc = cursor.next();
                Event event = new Event(doc.getInteger("disciplineId"),
                        doc.getString("location"),
                        doc.getDate("date"),
                        doc.getString("name"),
                        doc.getInteger("_id")
                );
                studentsDoc.add(event);
            }
        } finally {
            cursor.close();
        }
        return studentsDoc;
    }
}

