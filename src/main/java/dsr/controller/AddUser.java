package dsr.controller;

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

/**
 * this is the sign up servlet
 */
@WebServlet(
        name = "signUp",
        urlPatterns = "/signUp")
@Log4j2
public class AddUser extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String pw = req.getParameter("password1");
        String pw2 = req.getParameter("password2");
        String userName = req.getParameter("userName");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher;
        GenericDao<User> userDao = new GenericDao<>(User.class);
        List<User> userList = userDao.findByPropertyEqual("userName", userName);
        if(pw.equals(pw2) && !pw.equals("") && !userName.equals("") && userList.size() == 0) {

            UserAnime userAnime = new UserAnime();
            User newUser = new User(userAnime, userName, firstName, lastName, pw);
            userDao.insert(newUser);
            List<User> addedUser = userDao.findByPropertyEqual("userName", userName);
            int userId = addedUser.get(0).getId();
            log.info(newUser.getUserName());
            List<UserAnime> userAnimes = new ArrayList<>(addedUser.get(0).getUsersAnime());
            session.setAttribute("sessionId", userId);
            session.setAttribute("fullName", firstName + " " + lastName);
            session.setAttribute("userAnime", userAnimes);
            dispatcher = req.getRequestDispatcher("/my_anime.jsp");
            dispatcher.forward(req, resp);

        } else {
            log.info(pw + " " + pw2 + " " + userName);
            req.setAttribute("userFail", "invalid credentials");
            dispatcher = req.getRequestDispatcher("/sign_up.jsp");
            dispatcher.forward(req, resp);
        }
    }

}
