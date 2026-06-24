package com.jettra.plugin.company.controller;

import com.jettra.rest.annotations.accreditation.DeclareRoles;
import com.jettra.rest.annotations.accreditation.RolesAllowed;
import com.jettra.plugin.company.entity.Company;
import com.jettra.plugin.company.repository.CompanyRepository;
import com.jettra.rest.annotations.*;
import com.jettra.rest.core.Response;
import io.jettra.wui.core.annotations.Inject;
import java.util.List;

@Secured
@Path("/plugin/company/companys")
@DeclareRoles({"ADMIN", "MANAGER"})
@RolesAllowed({"ADMIN"})
public class CompanyController {

    @Inject
    CompanyRepository companyRepository;
    @GET
    @Produces("application/json")
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response save(Company company) {
       companyRepository.save(company);
        return Response.ok("Saved successfully").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response delete(@PathParam("id") String id) {
       companyRepository.delete(id);
        return Response.ok("Deleted successfully").build();
    }
}
