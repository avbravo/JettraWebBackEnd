package com.jettra.plugin.autentification.controller;

import com.jettra.rest.annotations.*;
import com.jettra.rest.core.Response;
import com.jettra.server.autentification.entity.JRole;
import com.jettra.server.autentification.repository.JRoleRepository;
import io.jettra.wui.core.annotations.Inject;
import java.util.List;
import java.util.UUID;

@Secured
@Path("/autentification/jroles")
@DeclareRoles({"ADMIN", "MANAGER"})
@RolesAllowed({"ADMIN"})
public class JRoleController {
@Inject
JRoleRepository jRoleRepository;
    @GET
    @Produces("application/json")
    public List<JRole> findAll() {
        return jRoleRepository.findAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response save(JRole jRole) {
        jRoleRepository.save(jRole);
        return Response.ok("Saved successfully").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response delete(@PathParam("id") String id) {
        UUID uuid = UUID.fromString(id);
        jRoleRepository.delete(uuid);
        return Response.ok("Deleted successfully").build();
    }
}
