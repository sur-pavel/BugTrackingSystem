package ru.surpavel.bugtrackingsystem.web.servlets.task;

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

import ru.surpavel.bugtrackingsystem.domain.Task;
import ru.surpavel.bugtrackingsystem.services.ServiceException;
import ru.surpavel.bugtrackingsystem.services.TaskService;

@Component("taskServlet")
public class TaskServlet implements HttpRequestHandler {
    private static final Logger log = LogManager.getLogger(TaskServlet.class.getName());

    private TaskService taskService;

    @Autowired
    public TaskServlet(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String id = request.getParameter("id");
        String jspPath;
        if (id != null) {
            try {
                Task task = taskService.findById(Integer.parseInt(id));
                request.setAttribute("task", task);
            } catch (ServiceException e) {
                log.error("Can't find task with id " + id, e);
                throw new ServletException("Can't find task with id " + id, e);
            }
            jspPath = "/WEB-INF/pages/task/taskInfo.jsp";
        } else {
            try {
                List<Task> tasks = taskService.findAll();
                request.setAttribute("tasks", tasks);
            } catch (ServiceException e) {
                log.error("Can't find all tasks", e);
                throw new ServletException("Can't find all tasks", e);
            }
            jspPath = "/WEB-INF/pages/task/taskList.jsp";
        }
        request.getRequestDispatcher(jspPath).forward(request, response);

    }

}
