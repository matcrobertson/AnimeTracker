package dsr.controller;

import dsr.entity.AnimeCards;
import dsr.entity.User;
import dsr.entity.UserAnime;
import dsr.persistence.GenericDao;
import lombok.extern.log4j.Log4j2;

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
        name = "deleteAnime",
        urlPatterns = "/deleteAnime"
)
@Log4j2
public class DeleteAnime extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao<UserAnime> userAnimeDao = new GenericDao<>(UserAnime.class);
        GenericDao<User> userDao = new GenericDao<>(User.class);
        HttpSession session = req.getSession();
        session.removeAttribute("animeCards");
        User user = userDao.getById((int)session.getAttribute("sessionId"));
        int animeId = Integer.parseInt(req.getParameter("animeId"));
        UserAnime animeToDelete = userAnimeDao.getById(animeId);
        userAnimeDao.delete(animeToDelete);
        User refreshUser = userDao.getById((int)session.getAttribute("sessionId"));
        CardEngine cardEngine = new CardEngine();
        ArrayList<AnimeCards> animeCards = cardEngine.makeCards(refreshUser.getId());

        session.setAttribute("animeCards", animeCards);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/my_anime.jsp");
        dispatcher.forward(req,resp);
    }
}
