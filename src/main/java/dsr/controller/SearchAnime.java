package dsr.controller;

import dsr.entity.AnimeCards;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet (
        name = "searchAnime",
        urlPatterns = "/searchAnime"
)
public class SearchAnime extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchTerm = req.getParameter("searchTerm");
        HttpSession session = req.getSession();

        CardEngine cardEngine = new CardEngine();
        ArrayList<AnimeCards> animeCards = cardEngine.searchCards(searchTerm, (int)session.getAttribute("sessionId"));
        req.setAttribute("animeCards", animeCards);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/my_anime.jsp");
        dispatcher.forward(req,resp);
    }
}
