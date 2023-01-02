package database;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;


public class HibernateMain {

    public static void main(String[] argv){
        createUser("Tester", "1111111");
        createUser("Gester2", "11111132451");
        createUser("Dzester", "1hsdklfja1");
        createUser("Tester", "1111111");
        createUser("Tester2", "1111112");
    }
    private static Session getSession() {
        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/militaryCalculator");
            configuration.setProperty("hibernate.connection.username", "postgres");
            configuration.setProperty("hibernate.connection.password", "12341234");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.addAnnotatedClass(User.class);  // Adds the Users class as a table
            SessionFactory factory = configuration.buildSessionFactory();

            return factory.openSession();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }


    public static boolean createUser(String username, String password){

        Session session = getSession();
        session.beginTransaction();
        //System.out.println(session);
        Transaction transaction = session.getTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);

        cr.select(root).where(cb.equal(root.get("username"), username));

        Query<User> query = session.createQuery(cr);
        List<User> results = query.getResultList();
        if (results.size() != 0) return false;
        User usr1 = new User(username, password);
        session.persist(usr1);
        transaction.commit();

        return true;
    }

    public static boolean checkUsersExistanse(String username, String password){

        Session session = getSession();
        session.beginTransaction();
        //System.out.println(session);
        Transaction transaction = session.getTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);

        cr.select(root).where(cb.equal(root.get("username"), username));

        Query<User> query = session.createQuery(cr);
        List<User> results = query.getResultList();
        if (results.size() != 0) return true;
        return false;
    }

    public static boolean canLogIn(String username, String password){
        if (!checkUsersExistanse(username, password)) return false;


        Session session = getSession();
        session.beginTransaction();
        //System.out.println(session);
        Transaction transaction = session.getTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);

        Predicate usernameRight = cb.equal(root.get("username"), username);
        Predicate passwordRight = cb.equal(root.get("password"), password);
        cr.select(root).where(cb.and(usernameRight, passwordRight));

        Query<User> query = session.createQuery(cr);
        List<User> results = query.getResultList();
        if (results.size() != 0) return true;
        return false;
    }
}