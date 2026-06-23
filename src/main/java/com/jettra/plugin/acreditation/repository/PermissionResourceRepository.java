package com.jettra.plugin.acreditation.repository;

import com.jettra.plugin.acreditation.entity.FeatureResource;
import com.jettra.plugin.acreditation.entity.PermissionResource;
import com.jettra.plugin.autentification.entity.Role;
import com.jettra.plugin.autentification.repository.RoleRepository;
import io.jettra.wui.core.annotations.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PermissionResourceRepository {

    private static List<FeatureResource> accreditationOptions = new ArrayList<>();
    private static List<PermissionResource> db = new ArrayList<>();
    @Inject
    FeatureResourceRepository accreditationOptionsRepository;

    @Inject
    RoleRepository roleRepository;

    static {

        List<Role> roles = RoleRepository.findAll();

        accreditationOptions = FeatureResourceRepository.findAll();

        for (Role r : roles) {
            for (FeatureResource ao : accreditationOptions) {
                addPermission(ao.name(), r, ao, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE);
            }
        }

        // Navigation / Administration / Credentials (secured/restricted)
            }

    private static void addPermission(String name, Role role, FeatureResource acreditationOptions, Boolean reader,
            Boolean write,
            Boolean delete,
            Boolean report) {

        db.add(new PermissionResource(
                UUID.nameUUIDFromBytes((name).getBytes()),
                role,
                acreditationOptions,
                reader,
                write,
                delete,
                report
        ));

    }

    public static List<PermissionResource> findAll() {
        return new ArrayList<>(db);
    }

    public static void save(PermissionResource record) {
        if (record.id() == null) {
            record = new PermissionResource(UUID.randomUUID(), record.role(), record.acreditationOptions(), record.reader(), record.write(),record.delete(),record.report());
        }
        delete(record.id().toString());
        db.add(record);
    }

    public static void delete(String id) {
        db.removeIf(r -> r.id().toString().equals(id));
    }

    public static Optional<PermissionResource> findById(String id) {
        return db.stream().filter(r -> r.id().toString().equals(id)).findFirst();
    }
}
