/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jettra.plugin.acreditation.entity;

import com.jettra.plugin.autentification.entity.Role;
import java.util.UUID;

/**
 *
 * @author avbravo
 */
public record AccreditationPermission(
        UUID id,
        Role role,
        AccreditationOption acreditationOptions,
        Boolean reader,
        Boolean write,
        Boolean delete,
        Boolean report
        ) {

}
