package com.jettra.plugin.acreditation.controller;

import com.jettra.plugin.acreditation.entity.AccreditationPermission;
import com.jettra.plugin.acreditation.repository.AccreditationPermissionRepository;
import com.jettra.rest.annotations.*;
import com.jettra.rest.core.Response;
import io.jettra.wui.core.annotations.Inject;
import java.util.List;

@Secured
@Path("/autentification/accreditationPermissions")
@DeclareRoles({"ADMIN", "USER"})
@RolesAllowed({"ADMIN"})
public class AccreditationPermissionController {
@Inject
   AccreditationPermissionRepository accreditationPermissionRepository;
    @GET
    @Produces("application/json")
    public List<AccreditationPermission> findAll() {
        return accreditationPermissionRepository.findAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response save(AccreditationPermission accreditationPermission) {
        accreditationPermissionRepository.save(accreditationPermission);
        return Response.ok("Saved successfully").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response delete(@PathParam("id") String id) {
        accreditationPermissionRepository.delete(id);
        return Response.ok("Deleted successfully").build();
    }
}
