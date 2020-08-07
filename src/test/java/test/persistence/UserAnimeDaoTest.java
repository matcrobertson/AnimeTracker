package test.persistence;

import dsr.entity.Anime;
import dsr.entity.User;
import dsr.entity.UserAnime;
import dsr.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class UserAnimeDaoTest {
    private GenericDao<UserAnime> userAnimeDao;
    private final Logger logger = LogManager.getLogger(this.getClass());


    @BeforeEach
    void setUp() {
        userAnimeDao = new GenericDao<>(UserAnime.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verify successful retrieval of a user
     */
    @Test
    void getByIdSuccess() {
        UserAnime retrievedUserAnime = (UserAnime) userAnimeDao.getById(1);
        assertEquals(1, retrievedUserAnime.getEpisodeNumber());

    }
    @Test
    void getAllSuccess() {
        List<UserAnime> users = userAnimeDao.getAll();
        assertEquals(3, users.size());
    }

    @Test
    void getByPropertyEqualSuccess() {
        List<UserAnime> users = userAnimeDao.findByPropertyEqual("seasonNumber", 1);
        assertEquals(2, users.size());
        assertEquals(1, users.get(0).getId());
    }

    @Test
    void updateSuccess() {
        int episodeWatched = 4;
        UserAnime userAnimeToUpdate = (UserAnime) userAnimeDao.getById(3);
        logger.debug(userAnimeToUpdate);
        userAnimeToUpdate.setEpisodeNumber(episodeWatched);
        userAnimeDao.saveOrUpdate(userAnimeToUpdate);
        UserAnime retrievedUser = (UserAnime) userAnimeDao.getById(3);//
        assertEquals(episodeWatched, retrievedUser.getEpisodeNumber());
    }
    /**
     * Verify successful insert of a user
     */
    @Test
    void insertSuccess() {
        GenericDao<User> userDao = new GenericDao<>(User.class);
        GenericDao<Anime> animeDao = new GenericDao<>(Anime.class);
        User currentUser = userDao.getById(1);
        Anime selectedAnime = (Anime) animeDao.findByPropertyEqual("title", "RWBY").get(0);
        UserAnime newUserAnime = new UserAnime(currentUser, selectedAnime, 1,1, "www.dot.com");

        int id = userAnimeDao.insert(newUserAnime);
        assertNotEquals(0,id);
        UserAnime insertedUserAnime = userAnimeDao.getById(id);
        assertEquals("RWBY", insertedUserAnime.getAnime().getTitle());
        assertEquals("bobsaget", insertedUserAnime.getUser().getUserName());
        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#mapping-model-pojo-equalshashcode
    }


    /**
     * Verify successful delete of user
     */
    @Test
    void deleteSuccess() {
        userAnimeDao.delete(userAnimeDao.getById(2));
        assertNull(userAnimeDao.getById(2));
    }

}
