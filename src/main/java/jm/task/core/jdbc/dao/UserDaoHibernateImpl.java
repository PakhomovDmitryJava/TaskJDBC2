package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Queue;


public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        String sql = "DROP TABLE IF EXISTS users";

        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = findUserById((int) id);
            session.delete(user);
            session.getTransaction().commit();
            System.out.println("\nStudent With Id = " + id + " Is Successfully Deleted From The Database!\n");
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }

        @Override
        public List<User> getAllUsers () {
            try (Session session = Util.getSessionFactory().openSession()) {
                return session.createQuery("from User", User.class).list();
            }
        }

        @Override
        public void cleanUsersTable() {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String sql = "TRUNCATE TABLE users";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
            session.close();

        }

        public static User findUserById(long id) {
            User user = null;
            try (Session session = Util.getSessionFactory().openSession()) {
                session.beginTransaction();
                user = (User) session.load(User.class,  id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return user;
        }
}
