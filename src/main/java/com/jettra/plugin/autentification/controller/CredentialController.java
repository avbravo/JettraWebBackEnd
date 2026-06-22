package com.jettra.plugin.autentification.controller;

import com.jettra.plugin.autentification.entity.Credential;
import com.jettra.plugin.autentification.repository.CredentialRepository;
import com.jettra.rest.annotations.*;
import com.jettra.rest.core.Response;
import io.jettra.wui.core.annotations.Inject;
import java.util.List;

@Secured
@Path("/autentification/credentials")
@DeclareRoles({"ADMIN", "USER"})
@RolesAllowed({"ADMIN"})
public class CredentialController {
@Inject
    CredentialRepository credentialRepository;
    @GET
    @Produces("application/json")
    public List<Credential> findAll() {
        return credentialRepository.findAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response save(Credential credential) {
       credentialRepository.save(credential);
        return Response.ok("Saved successfully").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response delete(@PathParam("id") String id) {
        credentialRepository.delete(id);
        return Response.ok("Deleted successfully").build();
    }
}
