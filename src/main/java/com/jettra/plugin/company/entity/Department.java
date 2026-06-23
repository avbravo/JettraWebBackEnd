/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jettra.plugin.company.entity;

import java.util.UUID;

/**
 *
 * @author avbravo
 */
public record Department(
    UUID id,
    String name,
    UUID parentDepartmentId
) {}
