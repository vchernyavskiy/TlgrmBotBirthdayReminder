package dao;

import models.Event;
import models.Tlguser;

import java.util.List;

public interface EventDAO {

//    public Event findById(int id);

    void save(Event event);
    void update(Event event);
    void delete(Event event);
    List<Event> findAllByUser(Tlguser tlguser);

}