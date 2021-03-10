package dao;

import models.Event;
import models.Tlguser;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

import java.time.LocalDate;
import java.util.List;

public class EventDAOImpl implements EventDAO {

    public void save(Event event) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(event);
        tx1.commit();
        session.close();
    }

    public List<Event> findAllByUser(Tlguser tlguser) {
        String q = "from Event where owner =: owner";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery(q).setParameter("owner", tlguser);
        List<Event> events = query.getResultList();
        session.close();

        return events;
    }

    @Override
    public List<Event> findAllByDate(LocalDate date) {
        String q = "from Event where MONTH(date) = MONTH(:date) and DAY(date) = DAY(:date)";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery(q).setParameter("date", date);
        List<Event> events = query.getResultList();
        session.close();

        return events;
    }

    public void delete(Event event) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(event);
        tx1.commit();
        session.close();
    }
}
