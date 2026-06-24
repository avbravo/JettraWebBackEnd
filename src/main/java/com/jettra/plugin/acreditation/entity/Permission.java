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
public record Permission(
        UUID id,
        Role role,
        Feature featureResource,
        Boolean reader,
        Boolean write,
        Boolean delete,
        Boolean report
        ) {

}
