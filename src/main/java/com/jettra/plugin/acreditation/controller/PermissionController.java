package com.jettra.plugin.acreditation.controller;

import com.jettra.rest.annotations.accreditation.DeclareRoles;
import com.jettra.rest.annotations.accreditation.RolesAllowed;
import com.jettra.plugin.acreditation.entity.Permission;
import com.jettra.plugin.acreditation.repository.PermissionRepository;
import com.jettra.rest.annotations.*;
import com.jettra.rest.core.Response;
import com.jettra.core.inject.annotation.Inject;
import java.util.List;

@Secured
@Path("/plugin/accreditation/permissionresources")
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
