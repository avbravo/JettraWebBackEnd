package com.jettra.plugin.company.controller;

import com.jettra.plugin.company.entity.Headquarters;
import com.jettra.plugin.company.repository.HeadquartersRepository;
import com.jettra.rest.annotations.*;
import com.jettra.rest.core.Response;
import io.jettra.wui.core.annotations.Inject;
import java.util.List;

@Secured
@Path("/autentification/headquarters")
@DeclareRoles({"ADMIN", "USER"})
@RolesAllowed({"ADMIN"})
public class HeadquartersController {

    @Inject
    HeadquartersRepository headquartersRepository;
    @GET
    @Produces("application/json")
    public List<Headquarters> findAll() {
        return headquartersRepository.findAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response save(Headquarters company) {
       headquartersRepository.save(company);
        return Response.ok("Saved successfully").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response delete(@PathParam("id") String id) {
       headquartersRepository.delete(id);
        return Response.ok("Deleted successfully").build();
    }
}
