package ru.surpavel.bugtrackingsystem.web.servlets.project;

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

import ru.surpavel.bugtrackingsystem.domain.Project;
import ru.surpavel.bugtrackingsystem.services.ProjectService;
import ru.surpavel.bugtrackingsystem.services.ServiceException;

@Component("projectServlet")
public class ProjectServlet implements HttpRequestHandler {
    private static final Logger log = LogManager.getLogger(ProjectServlet.class.getName());

    private ProjectService projectService;

    @Autowired
    public ProjectServlet(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String id = request.getParameter("id");
        String jspPath;
        if (id != null) {
            try {
                Project project = projectService.findById(Integer.parseInt(id));
                request.setAttribute("project", project);
            } catch (ServiceException e) {
                log.error("Can't find project with id " + id, e);
                throw new ServletException("Can't find project with id " + id, e);
            }
            jspPath = "/WEB-INF/pages/project/projectInfo.jsp";
        } else {
            try {
                List<Project> projects = projectService.findAll();
                request.setAttribute("projects", projects);
            } catch (ServiceException e) {
                log.error("Can't find all projects", e);
                throw new ServletException("Can't find all projects", e);
            }
            jspPath = "/WEB-INF/pages/project/projectList.jsp";
        }
        request.getRequestDispatcher(jspPath).forward(request, response);

    }

}
