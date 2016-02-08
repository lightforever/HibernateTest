import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by esemenov on 08.02.16.
 */
public class Main {

    public static void main(String [] args)
    {
        SessionFactory factory =null;
        Session session = null;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable exeptionOfFactoryCreation) {
            System.out.println("factory = new Configuration().configure().buildSessionFactory(); was not successfull");
            throw new ExceptionInInitializerError(exeptionOfFactoryCreation.getMessage());
        }
        session = factory.openSession();
        Query query = session.createQuery( "FROM Store emp JOIN FETCH emp.customers dep");
        List list = query.list();
        for (int i = 0; i < list.size(); i++) {
            Store store = (Store)list.get(i);
            System.out.println(String.format("Store name = %1$s Customers = %2$s",store.getName(),store.getCustomers().size()));
        }

    }
}
