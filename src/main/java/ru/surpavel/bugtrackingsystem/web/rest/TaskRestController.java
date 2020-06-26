package ru.surpavel.bugtrackingsystem.web.rest;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import ru.surpavel.bugtrackingsystem.domain.Project;
import ru.surpavel.bugtrackingsystem.domain.User;
import ru.surpavel.bugtrackingsystem.services.ProjectService;
import ru.surpavel.bugtrackingsystem.services.UserService;

@RestController
@Path("/projects")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class TaskRestController {

    private ProjectService projectService;

    private UserService userService;

    @Autowired
    public TaskRestController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @POST
    public Response create(Project project, @Context UriInfo uriInfo) {
        project = projectService.create(project);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(project.getId())).build();
        return Response.created(uri).entity(project).build();
    }

    @GET
    public List<Project> findAll() {
        return projectService.findAll();
    }

    @GET
    @Path("{projectId}")
    public Project findById(@PathParam("projectId") int projectId) {
        return projectService.findById(projectId);
    }

    @GET
    @Path("{projectId}/users")
    public List<User> findProjectUsers(@PathParam("projectId") int projectId) {
        return userService.findByProjectId(projectId);
    }

    @PUT
    @Path("{projectId}")
    public Response update(@PathParam("projectId") int projectId, Project project) {
        project.setId(projectId);
        return Response.status(Status.OK).entity(projectService.update(project)).build();
    }

    @DELETE
    @Path("{projectId}")
    public Response delete(@PathParam("projectId") int projectId) {
        projectService.delete(projectId);
        return Response.status(Status.NO_CONTENT).build();
    }

}
