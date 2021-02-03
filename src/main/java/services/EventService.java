package services;

import dao.EventDAOImpl;
import models.Event;

public class EventService {

    private static EventDAOImpl eventDAOImpl = new EventDAOImpl();

    public static void saveEvent(Event event) {
        eventDAOImpl.save(event);
    }

    public static void deleteEvent(Event event) {
        eventDAOImpl.delete(event);
    }

    public static void updateEvent(Event event) {
        eventDAOImpl.update(event);
    }

}