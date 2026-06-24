package com.jettra.plugin.acreditation.entity;

import io.jettra.wui.validations.NotNull;
import io.jettra.wui.validations.Size;
import java.util.UUID;

public record Role(
        @NotNull
        UUID id,
        @NotNull
        @Size(min = 3)
        String name,
        @NotNull
        Boolean active
        ) {

}
