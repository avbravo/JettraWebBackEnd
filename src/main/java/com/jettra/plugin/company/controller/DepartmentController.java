package com.jettra.plugin.company.controller;

import com.jettra.rest.annotations.accreditation.DeclareRoles;
import com.jettra.rest.annotations.accreditation.RolesAllowed;
import com.jettra.plugin.company.entity.Department;
import com.jettra.plugin.company.repository.DepartmentRepository;
import com.jettra.rest.annotations.*;
import com.jettra.rest.core.Response;
import com.jettra.core.inject.annotation.Inject;
import java.util.List;

@Secured
@Path("/plugin/company/departaments")
@DeclareRoles({"ADMIN", "MANAGER"})
@RolesAllowed({"ADMIN"})
public class DepartmentController {

    @Inject
    DepartmentRepository departamentRepository;
    @GET
    @Produces("application/json")
    public List<Department> findAll() {
        return departamentRepository.findAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response save(Department department) {
       departamentRepository.save(department);
        return Response.ok("Saved successfully").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response delete(@PathParam("id") String id) {
       departamentRepository.delete(id);
        return Response.ok("Deleted successfully").build();
    }
}
