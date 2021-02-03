package dao;

import models.Tlguser;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

public class TlguserDAOImpl implements TlguserDAO{

    public Tlguser findByChatId(Long chatId) {
        String q = "from Tlguser where chatid =: chatId";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery(q).setParameter("chatId", chatId);
        Tlguser tlguser = (Tlguser) query.uniqueResult();
        session.close();

        return tlguser;
    }

    public void save(Tlguser tlguser) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(tlguser);
        tx1.commit();
        session.close();
    }

    public void update(Tlguser tlguser) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(tlguser);
        tx1.commit();
        session.close();
    }

    public void delete(Tlguser tlguser) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(tlguser);
        tx1.commit();
        session.close();
    }

}
