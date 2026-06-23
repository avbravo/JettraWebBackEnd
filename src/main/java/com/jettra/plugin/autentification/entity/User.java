package com.jettra.plugin.autentification.entity;

import com.jettra.plugin.company.entity.Department;
import java.util.UUID;
import java.util.Set;

public record User(
    UUID id,
    String firstName,
    String lastName,
    String email,
    String phone,
    Boolean active,
    Set<Department> departments,
    Set<Role> roles
) {}
