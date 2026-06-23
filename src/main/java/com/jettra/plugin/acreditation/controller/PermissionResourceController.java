package com.jettra.plugin.acreditation.controller;

import com.jettra.plugin.acreditation.entity.PermissionResource;
import com.jettra.plugin.acreditation.repository.PermissionResourceRepository;
import com.jettra.rest.annotations.*;
import com.jettra.rest.core.Response;
import io.jettra.wui.core.annotations.Inject;
import java.util.List;

@Secured
@Path("/autentification/permissionresources")
@DeclareRoles({"ADMIN", "USER"})
@RolesAllowed({"ADMIN"})
public class PermissionResourceController {
@Inject
   PermissionResourceRepository accreditationPermissionRepository;
    @GET
    @Produces("application/json")
    public List<PermissionResource> findAll() {
        return accreditationPermissionRepository.findAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response save(PermissionResource accreditationPermission) {
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
