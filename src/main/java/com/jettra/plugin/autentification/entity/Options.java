package com.jettra.plugin.autentification.entity;

import java.util.UUID;

public record Options(
    UUID id,
    String name,
    String description,
    String resourcePath 
) {}
