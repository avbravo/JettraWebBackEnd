package com.jettra.plugin.acreditation.repository;

import com.jettra.jwt.enumerations.TypeAcreditationOptions;
import com.jettra.plugin.acreditation.entity.AccreditationOption;
import com.jettra.plugin.acreditation.entity.AccreditationPermission;
import com.jettra.plugin.autentification.entity.Role;
import com.jettra.plugin.autentification.repository.RoleRepository;
import io.jettra.wui.core.annotations.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AccreditationPermissionRepository {

    private static List<AccreditationOption> accreditationOptions = new ArrayList<>();
    private static List<AccreditationPermission> db = new ArrayList<>();
    @Inject
    AccreditationOptionRepository accreditationOptionsRepository;

    @Inject
    RoleRepository roleRepository;

    static {

        List<Role> roles = RoleRepository.findAll();

        accreditationOptions = AccreditationOptionRepository.findAll();

        for (Role r : roles) {
            for (AccreditationOption ao : accreditationOptions) {
                addPermission(ao.name(), r, ao, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE);
            }
        }

        // Navigation / Administration / Credentials (secured/restricted)
            }

    private static void addPermission(String name, Role role, AccreditationOption acreditationOptions, Boolean reader,
            Boolean write,
            Boolean delete,
            Boolean report) {
        //  db.add(new AccreditationOption(UUID.nameUUIDFromBytes((name).getBytes()), name, desc, path, typeAcreditationOptions));

        db.add(new AccreditationPermission(
                UUID.nameUUIDFromBytes((name).getBytes()),
                role,
                acreditationOptions,
                reader,
                write,
                delete,
                report
        ));

    }

    public static List<AccreditationPermission> findAll() {
        return new ArrayList<>(db);
    }

    public static void save(AccreditationPermission record) {
        if (record.id() == null) {
            record = new AccreditationPermission(UUID.randomUUID(), record.role(), record.acreditationOptions(), record.reader(), record.write(),record.delete(),record.report());
        }
        delete(record.id().toString());
        db.add(record);
    }

    public static void delete(String id) {
        db.removeIf(r -> r.id().toString().equals(id));
    }

    public static Optional<AccreditationPermission> findById(String id) {
        return db.stream().filter(r -> r.id().toString().equals(id)).findFirst();
    }
}
