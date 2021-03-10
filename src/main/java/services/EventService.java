package services;

import dao.EventDAOImpl;
import models.Event;
import models.Tlguser;

import java.time.LocalDate;
import java.util.List;

public class EventService {

    private static EventDAOImpl eventDAOImpl = new EventDAOImpl();

    public static void saveEvent(Event event) {
        eventDAOImpl.save(event);
    }

    public static List<Event> selectEventsByUser(Tlguser tlguser) {
        return eventDAOImpl.findAllByUser(tlguser);
    }

    public static List<Event> selectEventsByDate(LocalDate date) {
        return eventDAOImpl.findAllByDate(date);
    }

    public static void deleteEvent(Event event) {
        eventDAOImpl.delete(event);
    }
}