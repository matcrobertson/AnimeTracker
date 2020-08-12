package dsr.controller;

import dsr.entity.AnimeCards;
import dsr.entity.Seasons;
import dsr.entity.User;
import dsr.entity.UserAnime;
import dsr.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(
        name = "seasonsUpdate",
        urlPatterns = "/seasonsUpdate"
)
public class UpdateSeasonsAnime extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int seasonNumberUpdate = Integer.parseInt(req.getParameter("seasonNumber"));
        int episodeCount = Integer.parseInt(req.getParameter("episodeTotal"));
        int userAnimeId = Integer.parseInt(req.getParameter("userAnimeId"));
        GenericDao<UserAnime> userAnimeDao = new GenericDao<>(UserAnime.class);
        GenericDao<Seasons> seasonsDao = new GenericDao<>(Seasons.class);

        UserAnime userAnime = userAnimeDao.getById(userAnimeId);
        ArrayList<Seasons> animeSeasons = new ArrayList<>(userAnime.getAnime().getAnimeSeasons());
        boolean foundAnime = false;
        for(Seasons season: animeSeasons) {
            int seasonNumber = season.getSeasonNumber();
            if(seasonNumber == seasonNumberUpdate) {
                int seasonId = season.getId();
                Seasons seasonToUpdate = seasonsDao.getById(seasonId);
                seasonToUpdate.setEpisodeTotal(episodeCount);
                seasonsDao.saveOrUpdate(seasonToUpdate);
                foundAnime = true;
            }
        }
        if(!foundAnime) {
            String url = userAnime.getAnime().getTitle().replace(" ", "+") + "+Season+" + seasonNumberUpdate;
            Seasons season = new Seasons(userAnime.getAnime(), seasonNumberUpdate, episodeCount, "https://9anime.ru/filter?type%5B%5D=series&keyword=" + url);
            seasonsDao.insert(season);
        }
        HttpSession session = req.getSession();
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User refreshUser = userDao.getById((int)session.getAttribute("sessionId"));
        CardEngine cardEngine = new CardEngine();
        ArrayList<AnimeCards> animeCards = cardEngine.makeCards(refreshUser.getId());
        session.setAttribute("animeCards", animeCards);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/my_anime.jsp");
        dispatcher.forward(req,resp);
    }
}
