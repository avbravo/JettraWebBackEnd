package com.jettra.plugin.autentification.controller;

import com.jettra.plugin.autentification.entity.User;
import com.jettra.plugin.autentification.repository.UserRepository;
import com.jettra.rest.annotations.*;
import com.jettra.rest.core.Response;
import io.jettra.wui.core.annotations.Inject;
import java.util.List;

@Secured
@Path("/autentification/users")
@DeclareRoles({"ADMIN", "USER"})
@RolesAllowed({"ADMIN"})
public class UserController {

    @Inject
    UserRepository userRepository;
    @GET
    @Produces("application/json")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response save(User user) {
        userRepository.save(user);
        return Response.ok("Saved successfully").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response delete(@PathParam("id") String id) {
        userRepository.delete(id);
        return Response.ok("Deleted successfully").build();
    }
}
