package hibernateexample;
/* =================================

author ankitrajprasad created on 13/04/20 
inside the package - hibernateexample 

=====================================*/


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.Iterator;
import java.util.List;

public class QueryExample {

    private static SessionFactory factory;
    private static ServiceRegistry registry;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // factory = new Configuration().configure().buildSessionFactory();
            Configuration configuration = new Configuration().configure();
            registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            factory = configuration.buildSessionFactory(registry);
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        //HQL Examples
        Session session = factory.openSession();
        //Transaction tx = null;

        try {
            //1st Option
            /*Query query = session.createQuery("from Employee ");
            List employees = query.list();
            for (Iterator iterator =
                 employees.iterator(); iterator.hasNext(); ) {
                Employee ee = (Employee) iterator.next();
                System.out.print("First Name: " + ee.getFirstName());
                System.out.print("  Last Name: " + ee.getLastName());
                System.out.println("  Salary: " + ee.getSalary());
            }*/

            //2nd OPtion with alias
            /*Query query = session.createQuery("select e.firstName from Employee e");
            List employees = query.list();
            for (Iterator iterator =
                 employees.iterator(); iterator.hasNext(); ) {
                String firstName = (String) iterator.next();
                System.out.println("First Name: " + firstName);

            }*/

            //3rd option with limit in alias
            Query query = session.createQuery("from Employee  as e where e.firstName "+ "like 'S%' and salary>7000");
            List employees = query.list();
            for (Iterator iterator =
                 employees.iterator(); iterator.hasNext(); ) {
                Employee ee = (Employee) iterator.next();
                System.out.println("First Name: " + ee.getFirstName());
                System.out.println("Last Name: " + ee.getLastName());
                System.out.println("Salary: " + ee.getSalary());
            }


        } catch (HibernateException e) {
            // if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }


        StandardServiceRegistryBuilder.destroy(registry);
    }
}
