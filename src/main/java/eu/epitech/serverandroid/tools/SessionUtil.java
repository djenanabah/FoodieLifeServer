package eu.epitech.serverandroid.tools;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Vincent RAGOT
 */
public class SessionUtil {

    private static SessionFactory sessionFactory;

    /**
     *
     * @return Object of class SessionFactory
     */
    public static SessionFactory getSession() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return (sessionFactory);
    }
}