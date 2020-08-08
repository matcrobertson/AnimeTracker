package dsr.controller;

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
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name = "signIn",
        urlPatterns = "/signIn"
)
public class SignIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao<User> userDao = new GenericDao<>(User.class);

        String userName = req.getParameter("user_name");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        List<User> userList = userDao.findByPropertyEqual("userName", userName);
        if(userList.size() == 0) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/sign_in.jsp");
            dispatcher.forward(req, resp);
        }
        for(User user : userList) {
            if(user.getPassword().equals(password)) {
                session.setAttribute("sessionId", user.getId());
                session.setAttribute("fullName", user.getFirstName() + " " + user.getLastName());
                List<UserAnime> userAnimes = new ArrayList<>(user.getUsersAnime());
                session.setAttribute("userAnime", userAnimes);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/my_anime.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }
}
