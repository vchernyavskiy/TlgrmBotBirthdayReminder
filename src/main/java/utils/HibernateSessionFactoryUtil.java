package utils;

import models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.setProperty("hibernate.connection.url", System.getenv("tlg.db.url"));
                configuration.setProperty("hibernate.connection.username", System.getenv("mysqllogin"));
                configuration.setProperty("hibernate.connection.password", System.getenv("mysqlpassword"));
                configuration.addAnnotatedClass(Tlguser.class);
                configuration.addAnnotatedClass(Event.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
        return sessionFactory;
    }
}