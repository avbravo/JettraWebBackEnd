package com.jettra.plugin.autentification.entity;

import io.jettra.wui.validations.NotNull;
import io.jettra.wui.validations.Size;
import java.time.Instant;
import java.util.UUID;

public record Credential(
        @NotNull
        @Size(min = 3)
        UUID id,
        @NotNull
        User user,
        @NotNull
        @Size(min = 7)
        String username,
        @NotNull
        @Size(min = 10)
        String passwordHash,
        Boolean active,
        Instant lastLogin
        ) {

}
