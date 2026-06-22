package com.jettra.plugin.autentification.controller;

import com.jettra.jwt.JettraJWT;
import com.jettra.main.jwt.LoginResponse;
import com.jettra.plugin.autentification.entity.Credential;
import com.jettra.plugin.autentification.entity.Role;
import com.jettra.plugin.autentification.entity.User;
import com.jettra.plugin.autentification.repository.CredentialRepository;
import com.jettra.plugin.autentification.repository.UserRepository;
import com.jettra.rest.annotations.GET;
import com.jettra.rest.annotations.POST;
import com.jettra.rest.annotations.Path;
import com.jettra.rest.annotations.PermitAll;
import com.jettra.rest.annotations.Produces;
import com.jettra.rest.annotations.QueryParam;
import com.jettra.rest.core.Response;
import com.jettra.server.openapi.annotations.OpenApi;
import com.jettra.server.openapi.annotations.Operation;
import com.jettra.server.openapi.annotations.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Path("/autentification/auth")
@OpenApi(title = "Library API", version = "v1.0", description = "API for Library management")
public class AuthentificationController {

    private static final String JWT_SECRET = "default_secret_key_jettra_rest_2026";
    private static final long JWT_EXPIRATION = 3600000;

        @io.jettra.wui.core.annotations.Inject
    private CredentialRepository credentialRepository;
        
        @io.jettra.wui.core.annotations.Inject
    private UserRepository userRepository;
        
        
    
    @GET
    @Produces("application/json")
    @PermitAll
    @Operation(summary = "Login and get JWT", description = "Authenticates a user and returns a Bearer token")
    public Response login(
            @Parameter(name = "username", description = "Username for authentication", required = true) @QueryParam("username") String username,
            @Parameter(name = "password", description = "Password for authentication", required = true) @QueryParam("password") String password) {

        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("{\"error\":\"Invalid credentials\"}").build();
        }

//        Optional<Credential> optCred =  credentialRepository.findAll().stream()
//                .filter(c -> c.username().equals(username) && c.passwordHash().equals(password))
//                .findFirst();
        
        Optional<Credential> optCred =  credentialRepository.findByUsernamePassword(username, password);
               
        
        
        

        if (optCred.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("{\"error\":\"Invalid credentials\"}").build();
        }

        // Fetch User using UserRepository to validate/hydrate User
        Optional<User> optUser = userRepository.findById(optCred.get().user().id().toString());
        if (optUser.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("{\"error\":\"User not found in UserRepository\"}").build();
        }

        User user = optUser.get();
        List<String> roleNames = new ArrayList<>();
        if (user.roles() != null) {
            for (Role r : user.roles()) {
                roleNames.add(r.name());
            }
        }

        JettraJWT jwt = new JettraJWT(JWT_SECRET, JWT_EXPIRATION);
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roleNames);
        String token = "Bearer " + jwt.generateToken(claims, username);

        return Response.ok(new LoginResponse(token)).build();
    }
}
