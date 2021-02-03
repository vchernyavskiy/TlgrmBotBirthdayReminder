package dao;

import models.Event;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

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

}
