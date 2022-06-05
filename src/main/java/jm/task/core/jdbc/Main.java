package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        /*UserDaoJDBCImpl userDao = new UserDaoJDBCImpl(Util.getConnection());

        userDao.dropUsersTable();
        userDao.createUsersTable();

        userDao.saveUser("Name1", "LastName1", (byte) 20);
        userDao.saveUser("Name2", "LastName2", (byte) 25);
        userDao.saveUser("Name3", "LastName3", (byte) 31);
        userDao.saveUser("Name4", "LastName4", (byte) 38);

        userDao.removeUserById(1);
        userDao.removeUserById(2);
        userDao.removeUserById(3);
        userDao.removeUserById(4);
        userDao.getAllUsers().stream().forEach(System.out::println);
       userDao.cleanUsersTable();
       userDao.dropUsersTable();*/

        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
        userDao.dropUsersTable();
        userDao.createUsersTable();
        userDao.saveUser("Name1", "LastName2342341", (byte) 20);
        userDao.saveUser("Name2", "LastName2342342", (byte) 25);
        userDao.saveUser("Name3", "LastName2342343", (byte) 31);
        userDao.saveUser("Name4123", "LastName234234", (byte) 38);
        userDao.getAllUsers().stream().forEach(System.out::println);
        userDao.removeUserById(1);
        userDao.removeUserById(2);
        userDao.removeUserById(3);
        userDao.removeUserById(4);

        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
