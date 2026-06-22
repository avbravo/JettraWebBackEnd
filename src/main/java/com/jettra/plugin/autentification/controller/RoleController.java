package com.jettra.plugin.autentification.controller;

import com.jettra.plugin.autentification.entity.Role;
import com.jettra.plugin.autentification.repository.RoleRepository;
import com.jettra.rest.annotations.*;
import com.jettra.rest.core.Response;
import io.jettra.wui.core.annotations.Inject;
import java.util.List;

@Secured
@Path("/autentification/roles")
@DeclareRoles({"ADMIN", "USER"})
@RolesAllowed({"ADMIN"})
public class RoleController {
@Inject
RoleRepository roleRepository;
    @GET
    @Produces("application/json")
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response save(Role role) {
        roleRepository.save(role);
        return Response.ok("Saved successfully").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response delete(@PathParam("id") String id) {
        roleRepository.delete(id);
        return Response.ok("Deleted successfully").build();
    }
}
