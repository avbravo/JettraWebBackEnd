package com.jettra.plugin.autentification.controller;

import com.jettra.plugin.autentification.entity.Permission;
import com.jettra.plugin.autentification.repository.PermissionRepository;
import com.jettra.rest.annotations.*;
import com.jettra.rest.core.Response;
import io.jettra.wui.core.annotations.Inject;
import java.util.List;

@Secured
@Path("/autentification/permissions")
@DeclareRoles({"ADMIN", "USER"})
@RolesAllowed({"ADMIN"})
public class PermissionController {
@Inject
   PermissionRepository permissionRepository;
    @GET
    @Produces("application/json")
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response save(Permission permission) {
        permissionRepository.save(permission);
        return Response.ok("Saved successfully").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response delete(@PathParam("id") String id) {
        permissionRepository.delete(id);
        return Response.ok("Deleted successfully").build();
    }
}
