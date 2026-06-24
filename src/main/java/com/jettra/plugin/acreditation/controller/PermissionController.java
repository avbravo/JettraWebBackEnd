package com.jettra.plugin.acreditation.controller;

import com.jettra.plugin.acreditation.entity.Permission;
import com.jettra.plugin.acreditation.repository.PermissionRepository;
import com.jettra.rest.annotations.*;
import com.jettra.rest.core.Response;
import io.jettra.wui.core.annotations.Inject;
import java.util.List;

@Secured
@Path("/autentification/permissionresources")
@DeclareRoles({"ADMIN", "MANAGER"})
@RolesAllowed({"ADMIN"})
public class PermissionController {
@Inject
   PermissionRepository permissionResourceRepository;
    @GET
    @Produces("application/json")
    public List<Permission> findAll() {
        return permissionResourceRepository.findAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response save(Permission accreditationPermission) {
        permissionResourceRepository.save(accreditationPermission);
        return Response.ok("Saved successfully").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response delete(@PathParam("id") String id) {
        permissionResourceRepository.delete(id);
        return Response.ok("Deleted successfully").build();
    }
}
