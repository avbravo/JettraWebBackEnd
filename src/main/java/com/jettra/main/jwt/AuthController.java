package com.jettra.main.jwt;

import com.jettra.jwt.request.LoginRequest;
import com.jettra.jwt.request.LoginResponse;
import com.jettra.jwt.JettraJWT;
import com.jettra.rest.annotations.Consumes;
import com.jettra.rest.annotations.POST;
import com.jettra.rest.annotations.Path;
import com.jettra.rest.annotations.PermitAll;
import com.jettra.rest.annotations.Produces;
import com.jettra.rest.core.Response;
import com.jettra.server.autentification.entity.JCredential;
import com.jettra.server.autentification.entity.JRole;
import com.jettra.server.autentification.entity.JUser;
import com.jettra.server.autentification.repository.JCredentialRepository;
import com.jettra.server.openapi.annotations.OpenApi;
import com.jettra.server.openapi.annotations.Operation;
import com.jettra.server.openapi.annotations.RequestBody;
import com.jettra.server.openapi.annotations.ApiResponse;
import io.jettra.wui.core.annotations.Inject;

import java.util.HashMap;

@OpenApi(title = "Authentication API", version = "v1.0", description = "API for Authentication")
@Path("/auth")
public class AuthController {

    private static final String JWT_SECRET = "default_secret_key_jettra_rest_2026";
    private static final long JWT_EXPIRATION = 3600000; // 1 hour

    @Inject
    JCredentialRepository jCredentialRepository;
    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    @PermitAll
    @Operation(summary = "Login and get JWT", description = "Authenticates a user and returns a Bearer token")
    @ApiResponse(responseCode = "200", description = "Successful authentication")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    public Response login(@RequestBody(description = "Login credentials", required = true) LoginRequest request) {
        if (request == null || request.getUsername() == null || request.getPassword() == null 
            || request.getUsername().trim().isEmpty() || request.getPassword().trim().isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("{\"error\":\"Invalid credentials\"}").build();
        }

        java.util.Optional<JCredential> optCred = jCredentialRepository.findAll().stream()
                .filter(c -> c.username().equals(request.getUsername()) && c.passwordHash().equals(request.getPassword()))
                .findFirst();

        if (optCred.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("{\"error\":\"Invalid credentials\"}").build();
        }

       JUser jUser = optCred.get().jUser();
        java.util.List<String> roleNames = new java.util.ArrayList<>();
        if (jUser != null && jUser.jRoles()!= null) {
            for (JRole r : jUser.jRoles()) {
                roleNames.add(r.name());
            }
        }

        JettraJWT jwt = new JettraJWT(JWT_SECRET, JWT_EXPIRATION);
        java.util.Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roleNames);
        String token = "Bearer " + jwt.generateToken(claims, request.getUsername());

        return Response.ok(new LoginResponse(token)).build();
    }
}
