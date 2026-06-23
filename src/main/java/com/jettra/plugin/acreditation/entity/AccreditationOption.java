package com.jettra.plugin.acreditation.entity;

import com.jettra.jwt.enumerations.TypeAcreditationOptions;
import java.util.UUID;

public record AccreditationOption(
        UUID id,
        String name,
        String description,
        String resourcePath,
        TypeAcreditationOptions typeAcreditationOptions
        ) {

}
