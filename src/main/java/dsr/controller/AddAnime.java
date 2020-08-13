package dsr.controller;

import dsr.entity.*;
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
import java.util.List;

@WebServlet(
        name = "addAnime",
        urlPatterns = "/addAnime"
)
public class AddAnime extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        get info from the form
        String title = req.getParameter("animeTitle");
        String imageUrl = req.getParameter("imageUrl");
        int episodeTotal = Integer.parseInt(req.getParameter("episodeTotal"));
        HttpSession session = req.getSession();
        int userId = (int)session.getAttribute("sessionId");

//        get the user's id for adding to UserAnime
        GenericDao<User> userDao = new GenericDao<>(User.class);
        GenericDao<Anime> animeDao = new GenericDao<>(Anime.class);
        GenericDao<UserAnime> userAnimeDao = new GenericDao<>(UserAnime.class);
        GenericDao<Seasons> seasonsDao = new GenericDao<>(Seasons.class);
        List<Anime> newAnime = animeDao.findByPropertyEqual("title",title);
        User currentUser = userDao.getById(userId);

        Anime anime;



        if(newAnime.size() == 0) {

            if(!title.equals("") && title != null && !imageUrl.equals("") && imageUrl != null){
                anime = new Anime(title, imageUrl);
            } else {
                anime = new Anime(title, "images/fillerCard.jpg");
            }
            String buildNewUrl = "https://9anime.ru/filter?type%5B%5D=series&keyword=" + anime.getTitle().replace(" ", "+");
            animeDao.insert(anime);
            Seasons seasons = new Seasons(anime,1,episodeTotal, buildNewUrl);
            seasonsDao.insert(seasons);
            anime = animeDao.getById(anime.getId());
        } else {
            int animeId = newAnime.get(0).getId();
            GenericDao<Anime> newAnimeDao = new GenericDao<>(Anime.class);
            anime = newAnimeDao.getById(animeId);
            if(anime.getImageUrl().equals("images/fillerCard.jpg") && imageUrl != null) {

                anime.setImageUrl(imageUrl);
                newAnimeDao.saveOrUpdate(anime);
                anime = animeDao.getById(anime.getId());
            }
        }

        List<Seasons> availableSeasons = new ArrayList<>(anime.getAnimeSeasons());
        UserAnime userAnime = new UserAnime(currentUser,anime,1,1, availableSeasons.get(0).getSearchUrl());
        userAnimeDao.insert(userAnime);
        User refreshUser = userDao.getById((int)session.getAttribute("sessionId"));
        CardEngine cardEngine = new CardEngine();
        ArrayList<AnimeCards> animeCards = cardEngine.makeCards(refreshUser.getId());
        session.setAttribute("animeCards", animeCards);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/my_anime.jsp");
        dispatcher.forward(req,resp);
    }
}
