package test.persistence;

import dsr.entity.Anime;
import dsr.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class AnimeDaoTest {
    private GenericDao<Anime> genericDao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        genericDao = new GenericDao<>(Anime.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verify successful retrieval of a user
     */
    @Test
    void getByIdSuccess() {
        Anime retrievedUser = (Anime) genericDao.getById(1);
        assertEquals("naruto", retrievedUser.getTitle());

    }
    @Test
    void getByPropertyEqualSuccess() {
        List<Anime> anime = genericDao.findByPropertyEqual("title", "naruto");
        assertEquals(1, anime.size());
        assertEquals(1, anime.get(0).getId());
    }

    @Test
    void updateSuccess() {
        String newTitle = "Flying Spaghetti Monster";
        Anime animeToUpdate = (Anime)genericDao.getById(3);
        logger.debug(animeToUpdate);
        animeToUpdate.setTitle(newTitle);
        genericDao.saveOrUpdate(animeToUpdate);
        Anime retrievedUser = (Anime)genericDao.getById(3);//
        assertEquals(newTitle, retrievedUser.getTitle());
    }
    /**
     * Verify successful insert of a user
     */
    @Test
    void insertSuccess() {

        Anime newUser = new Anime("that Show");

        int id = genericDao.insert(newUser);
        assertNotEquals(0,id);
        Anime insertedAnime = genericDao.getById(id);
        assertEquals("that Show", insertedAnime.getTitle());
        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#mapping-model-pojo-equalshashcode
    }

}
