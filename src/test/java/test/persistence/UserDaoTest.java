package test.persistence;


import dsr.entity.User;
import dsr.entity.UserAnime;
import dsr.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {

        private final Logger logger = LogManager.getLogger(this.getClass());
        private GenericDao<User> genericDao;


        /**
         * Run set up tasks before each test:
         * 1. execute sql which deletes everything from the table and inserts records)
         * 2. Create any objects needed in the tests
         */
        @BeforeEach
        void setUp() {
            genericDao = new GenericDao<>(User.class);
            Database database = Database.getInstance();
            database.runSQL("cleandb.sql");

        }

        /**
         * Verify successful retrieval of a user
         */
        @Test
        void getByIdSuccess() {
            User retrievedUser = (User) genericDao.getById(1);
            assertEquals("bobsaget", retrievedUser.getUserName());

        }
    @Test
    void getAllSuccess() {
        List<User> users = genericDao.getAll();
        assertEquals(3, users.size());
    }

    @Test
    void getByPropertyEqualSuccess() {
        List<User> users = genericDao.findByPropertyEqual("userName", "bobsaget");
        assertEquals(1, users.size());
        assertEquals(1, users.get(0).getId());
    }

    @Test
    void updateSuccess() {
        String newFirstName = "Flying Spaghetti Monster";
        User userToUpdate = (User)genericDao.getById(3);
        logger.debug(userToUpdate);
        userToUpdate.setFirstName(newFirstName);
        genericDao.saveOrUpdate(userToUpdate);
        User retrievedUser = (User)genericDao.getById(3);//
        assertEquals(newFirstName, retrievedUser.getFirstName());
    }
    /**
     * Verify successful insert of a user
     */
    @Test
    void insertSuccess() {
        UserAnime userAnime = new UserAnime();
        User newUser = new User(userAnime,"jimmihendrix", "jimmi", "hendrix", "guitar");

        int id = genericDao.insert(newUser);
        assertNotEquals(0,id);
        User insertedUser = genericDao.getById(id);
        assertEquals("jimmihendrix", insertedUser.getUserName());
        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#mapping-model-pojo-equalshashcode
    }


    /**
     * Verify successful delete of user
     */
    @Test
    void deleteSuccess() {
        genericDao.delete(genericDao.getById(2));
        assertNull(genericDao.getById(2));
    }





}