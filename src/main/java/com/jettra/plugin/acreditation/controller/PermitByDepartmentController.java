package com.jettra.plugin.acreditation.controller;

import com.jettra.plugin.acreditation.entity.PermitByDepartment;
import com.jettra.plugin.acreditation.repository.PermitByDepartmentRepository;
import com.jettra.rest.annotations.*;
import com.jettra.rest.core.Response;
import io.jettra.wui.core.annotations.Inject;
import java.util.List;

@Secured
@Path("/autentification/featureresources")
@DeclareRoles({"ADMIN", "USER"})
@RolesAllowed({"ADMIN"})
public class PermitByDepartmentController {
@Inject
   PermitByDepartmentRepository userDepartmentRoleResourceRepository;
    @GET
    @Produces("application/json")
    public List<PermitByDepartment> findAll() {
        return userDepartmentRoleResourceRepository.findAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response save(PermitByDepartment accreditationOption) {
        userDepartmentRoleResourceRepository.save(accreditationOption);
        return Response.ok("Saved successfully").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response delete(@PathParam("id") String id) {
        userDepartmentRoleResourceRepository.delete(id);
        return Response.ok("Deleted successfully").build();
    }
}
