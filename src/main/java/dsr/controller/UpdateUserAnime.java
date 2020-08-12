package dsr.controller;

import dsr.entity.AnimeCards;
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
        name = "updateAnime",
        urlPatterns = "/updateAnime"
)
public class UpdateUserAnime extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String linkUpdate = req.getParameter("linkUpdate");
        String seasonUpdate = req.getParameter("seasonNumber");
        String episodeUpdate = req.getParameter("episodeNumber");



        int userAnimeId = Integer.parseInt(req.getParameter("userAnimeId"));
        GenericDao<UserAnime> userAnimeDao = new GenericDao<>(UserAnime.class);
        GenericDao<User> userDao = new GenericDao<>(User.class);
        UserAnime userAnime = userAnimeDao.getById(userAnimeId);

        if(linkUpdate != null && !linkUpdate.equals("")) {
            userAnime.setUserAnimeLink(linkUpdate);
        }

        if(seasonUpdate != null && episodeUpdate != null && !seasonUpdate.equals("") && !episodeUpdate.equals("")) {
            userAnime.setEpisodeNumber(Integer.parseInt(episodeUpdate));
            userAnime.setSeasonNumber(Integer.parseInt(seasonUpdate));
        }
        userAnimeDao.saveOrUpdate(userAnime);
        User refreshUser = userDao.getById((int)session.getAttribute("sessionId"));
        CardEngine cardEngine = new CardEngine();
        ArrayList<AnimeCards> animeCards = cardEngine.makeCards(refreshUser.getId());
        session.setAttribute("animeCards", animeCards);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/my_anime.jsp");
        dispatcher.forward(req,resp);
    }
}
