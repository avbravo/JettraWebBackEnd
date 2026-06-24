package com.jettra.plugin.acreditation.controller;

import com.jettra.rest.annotations.accreditation.DeclareRoles;
import com.jettra.rest.annotations.accreditation.RolesAllowed;
import com.jettra.plugin.acreditation.entity.User;
import com.jettra.plugin.acreditation.repository.UserRepository;
import com.jettra.rest.annotations.*;
import com.jettra.rest.core.Response;
import com.jettra.core.inject.annotation.Inject;
import java.util.List;

@Secured
@Path("/plugin/accreditation/users")
@DeclareRoles({"ADMIN", "MANAGER"})
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
