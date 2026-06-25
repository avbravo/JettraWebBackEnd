package com.jettra.plugin.acreditation.entity;

import io.jettra.jwt.enumerations.TypeFeatureResource;
import java.util.UUID;

public record Feature(
        UUID id,
        String name,
        String description,
        String resourcePath,
        TypeFeatureResource typeFeatureResource
        ) {

}
