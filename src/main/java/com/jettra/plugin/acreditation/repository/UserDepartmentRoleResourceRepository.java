package com.jettra.plugin.acreditation.repository;

import com.jettra.plugin.acreditation.entity.UserDerpartmentRoleResource;

import com.jettra.plugin.autentification.entity.Role;
import com.jettra.plugin.autentification.entity.User;
import com.jettra.plugin.autentification.repository.RoleRepository;
import com.jettra.plugin.autentification.repository.UserRepository;
import com.jettra.plugin.company.entity.Department;
import com.jettra.plugin.company.repository.DepartmentRepository;
import io.jettra.wui.core.annotations.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserDepartmentRoleResourceRepository {

    // private static List<FeatureResource> featureResources = new ArrayList<>();
    private static List<UserDerpartmentRoleResource> db = new ArrayList<>();
    @Inject
    static DepartmentRepository deparmentRepository;
    @Inject
    static UserRepository userRepository;
    @Inject
    static RoleRepository roleRepository;


    static {

        List<Role> roles = roleRepository.findAll();
        List<User> users = userRepository.findAll();
        List<Department> departaments = deparmentRepository.findAll();
        for (User u : users) {
            for (Role r : roles) {
                for (Department d : departaments) {
                    addPermission(u, r, d, Boolean.TRUE);                
                  }
              }
        }
            }

    private static void addPermission(User user, Role role, Department department, Boolean active) {

        db.add(new UserDerpartmentRoleResource(
                UUID.nameUUIDFromBytes(user.firstName().getBytes()),
                user,
                role,
                department,
                active)
        );

    }

    public static List<UserDerpartmentRoleResource> findAll() {
        return new ArrayList<>(db);
    }

    public static void save(UserDerpartmentRoleResource record) {
        if (record.id() == null) {
            record = new UserDerpartmentRoleResource(
                    UUID.randomUUID(),
                    record.user(),
                     record.role(),
                    record.departament(),
                   
                    record.active()
            );
        }

        delete(record.id().toString());
        db.add(record);
    }

    public static void delete(String id) {
        db.removeIf(r -> r.id().toString().equals(id));
    }

    public static Optional<UserDerpartmentRoleResource> findById(String id) {
        return db.stream().filter(r -> r.id().toString().equals(id)).findFirst();
    }
}
