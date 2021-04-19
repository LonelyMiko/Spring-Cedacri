package md.cedacri.spring.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Utils of HIbernate that allow to access the DB
 */
public class HibernateUtils {
    //Constuctor privat pentru a nu putea crea un obiect a clasei date din cauza ca classa are metode statice
    private HibernateUtils() {
    }

    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    /**
     * Create a sessionFactory object with the parameters specified on hibernate.cfg.xml
     *
     * @return SessionFactory object
     */
    private static SessionFactory buildSessionFactory() {
        SessionFactory sessionFactory;
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Initial SessionFactory creation failed: " + e);
            throw new ExceptionInInitializerError(e);
        }
        return sessionFactory;
    }

    /**
     * Retrieve a SessionFactory object
     *
     * @return SessionFactory object
     */
    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    /**
     * Close the connection to the DB
     */
    public static void shutdown() {
        getSessionFactory().close();
    }
}
