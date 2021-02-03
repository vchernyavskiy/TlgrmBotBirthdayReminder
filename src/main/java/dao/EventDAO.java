package dao;

import models.Event;

public interface EventDAO {

//    public Event findById(int id);

    void save(Event event);
    void update(Event event);
    void delete(Event event);

    //    public List<Event> findAll();

}