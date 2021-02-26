package dao;

import models.Event;
import models.Tlguser;

import java.util.List;

public interface EventDAO {
    void save(Event event);

    //void update(Event event);
    void delete(Event event);

    List<Event> findAllByUser(Tlguser tlguser);
//    Event findById(int id);
}