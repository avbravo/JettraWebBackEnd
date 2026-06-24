package com.jettra.plugin.acreditation.controller;

import com.jettra.plugin.acreditation.entity.Feature;
import com.jettra.plugin.acreditation.repository.FeatureRepository;
import com.jettra.rest.annotations.*;
import com.jettra.rest.core.Response;
import io.jettra.wui.core.annotations.Inject;
import java.util.List;

@Secured
@Path("/autentification/featureresources")
@DeclareRoles({"ADMIN", "USER"})
@RolesAllowed({"ADMIN"})
public class FeatureController {
@Inject
   FeatureRepository accreditationOptionRepository;
    @GET
    @Produces("application/json")
    public List<Feature> findAll() {
        return accreditationOptionRepository.findAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response save(Feature accreditationOption) {
        accreditationOptionRepository.save(accreditationOption);
        return Response.ok("Saved successfully").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response delete(@PathParam("id") String id) {
        accreditationOptionRepository.delete(id);
        return Response.ok("Deleted successfully").build();
    }
}
