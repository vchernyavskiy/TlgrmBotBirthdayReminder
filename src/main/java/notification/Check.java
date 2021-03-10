package notification;

import models.Event;
import services.EventService;

import java.time.LocalDate;
import java.util.List;

public class Check{

    public static List<Event> getEvents(LocalDate date){
        List<Event> events = EventService.selectEventsByDate(date);
        return events;
    }


}
