/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.jettra.plugin.acreditation.entity;

import com.jettra.plugin.autentification.entity.Role;
import com.jettra.plugin.autentification.entity.User;
import com.jettra.plugin.company.entity.Department;
import io.jettra.wui.validations.NotNull;
import io.jettra.wui.validations.Size;
import java.util.UUID;

/**
 *
 * @author avbravo Description: Administra los roles por departamento por user.
 * Se usa para casos en los que las validaciones son necesarias para usuarios
 * con varios roles por departamento.
 */
public record UserDerpartmentRoleResource(
        @NotNull
        @Size(min = 3)
        UUID id,
        @NotNull
        User user,
        @NotNull
        Role role,
        @NotNull
        Department departament,
        @NotNull
        Boolean active
        ) {

}
