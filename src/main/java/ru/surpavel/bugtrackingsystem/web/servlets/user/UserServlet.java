package ru.surpavel.bugtrackingsystem.web.servlets.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import ru.surpavel.bugtrackingsystem.domain.User;
import ru.surpavel.bugtrackingsystem.services.UserService;
import ru.surpavel.bugtrackingsystem.services.ServiceException;

@Component("userServlet")
public class UserServlet implements HttpRequestHandler {
    private static final Logger log = LogManager.getLogger(UserServlet.class.getName());

    private UserService userService;

    @Autowired
    public UserServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String id = request.getParameter("id");
        String jspPath;
        if (id != null) {
            try {
                User user = userService.findById(Integer.parseInt(id));
                request.setAttribute("user", user);
            } catch (ServiceException e) {
                log.error("Can't find user with id " + id, e);
                throw new ServletException("Can't find user with id " + id, e);
            }
            jspPath = "/WEB-INF/pages/user/userInfo.jsp";
        } else {
            try {
                List<User> users = userService.findAll();
                request.setAttribute("users", users);
            } catch (ServiceException e) {
                log.error("Can't find all users", e);
                throw new ServletException("Can't find all users", e);
            }
            jspPath = "/WEB-INF/pages/user/userList.jsp";
        }
        request.getRequestDispatcher(jspPath).forward(request, response);

    }

}
