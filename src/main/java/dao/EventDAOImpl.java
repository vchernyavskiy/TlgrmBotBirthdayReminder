package dao;

import models.Event;
import models.Tlguser;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class EventDAOImpl implements EventDAO{

    public void save(Event event) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(event);
        tx1.commit();
        session.close();
    }

    public void update(Event event) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(event);
        tx1.commit();
        session.close();
    }

    public void delete(Event event) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(event);
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
}
