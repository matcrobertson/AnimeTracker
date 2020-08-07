package test.persistence;

import dsr.entity.Anime;
import dsr.entity.Seasons;
import dsr.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SeasonsDaoTest
{
    private GenericDao<Seasons> genericDao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        genericDao = new GenericDao<>(Seasons.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verify successful retrieval of a user
     */
    @Test
    void getByIdSuccess() {
        Seasons retrievedSeason = (Seasons) genericDao.getById(1);
        assertEquals(25, retrievedSeason.getEpisodeTotal());

    }
    @Test
    void getByPropertyEqualSuccess() {
        List<Seasons> animeSeason = genericDao.findByPropertyEqual("episodeTotal", 5);
        assertEquals(2, animeSeason.size());
        assertEquals(3, animeSeason.get(0).getId());
    }

    @Test
    void updateSuccess() {
        int newEpisodeTotal = 26;
        Seasons seasonToUpdate = (Seasons) genericDao.getById(1);
        logger.debug(seasonToUpdate);
        seasonToUpdate.setEpisodeTotal(newEpisodeTotal);
        genericDao.saveOrUpdate(seasonToUpdate);
        Seasons retrievedSeason = (Seasons)genericDao.getById(3);//
        assertEquals(newEpisodeTotal, seasonToUpdate.getEpisodeTotal());
    }
    /**
     * Verify successful insert of a user
     */
    @Test
    void insertSuccess() {
        GenericDao<Anime> animeDao = new GenericDao<>(Anime.class);
        Anime anime = (Anime)animeDao.getById(3);
        Seasons newSeason = new Seasons(anime, 4, 24, "www.dot.com");

        int id = genericDao.insert(newSeason);
        assertNotEquals(0,id);
        Seasons insertedSeason = genericDao.getById(id);
        assertEquals(24, insertedSeason.getEpisodeTotal());
        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#mapping-model-pojo-equalshashcode
    }

    @Test
    void deleteSuccess() {
        genericDao.delete(genericDao.getById(2));
        assertNull(genericDao.getById(2));
    }
}
