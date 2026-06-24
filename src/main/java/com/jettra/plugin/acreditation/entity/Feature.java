package com.jettra.plugin.acreditation.entity;

import com.jettra.jwt.enumerations.TypeFeatureResource;
import java.util.UUID;

public record Feature(
        UUID id,
        String name,
        String description,
        String resourcePath,
        TypeFeatureResource typeFeatureResource
        ) {

}
