package dao;

import models.Event;
import models.Tlguser;

import java.time.LocalDate;
import java.util.List;

public interface EventDAO {
    void save(Event event);
    List<Event> findAllByUser(Tlguser tlguser);
    List<Event> findAllByDate(LocalDate date);
    void delete(Event event);
}