package com.jettra.plugin.acreditation.repository;

import com.jettra.plugin.acreditation.entity.PermitByDepartment;

import com.jettra.plugin.acreditation.entity.Role;
import com.jettra.plugin.acreditation.entity.User;
import com.jettra.plugin.company.entity.Department;
import com.jettra.plugin.company.repository.DepartmentRepository;
import io.jettra.wui.core.annotations.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PermitByDepartmentRepository {

    // private static List<FeatureResource> featureResources = new ArrayList<>();
    private static List<PermitByDepartment> db = new ArrayList<>();
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

        db.add(new PermitByDepartment(
                UUID.nameUUIDFromBytes(user.firstName().getBytes()),
                user,
                role,
                department,
                active)
        );

    }

    public static List<PermitByDepartment> findAll() {
        return new ArrayList<>(db);
    }

    public static void save(PermitByDepartment record) {
        if (record.id() == null) {
            record = new PermitByDepartment(
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

    public static Optional<PermitByDepartment> findById(String id) {
        return db.stream().filter(r -> r.id().toString().equals(id)).findFirst();
    }
}
