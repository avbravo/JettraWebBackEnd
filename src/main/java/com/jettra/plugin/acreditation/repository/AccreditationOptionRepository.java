package com.jettra.plugin.acreditation.repository;

import com.jettra.jwt.enumerations.TypeAcreditationOptions;
import com.jettra.plugin.acreditation.entity.AccreditationOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AccreditationOptionRepository {
    private static final List<AccreditationOption> db = new ArrayList<>();

    static {
        // Navigation / Administration / Credentials (secured/restricted)
        addOptions("PANEL_PRINCIPAL", "Panel Principal", "/dashboard", TypeAcreditationOptions.MENU);
        addOptions("PERMISOS", "Administrar Permisos", "/permiso", TypeAcreditationOptions.MENU);
        addOptions("ROLES", "Administrar Roles", "/rol", TypeAcreditationOptions.MENU);
        addOptions("PERFILES", "Administrar Perfiles", "/perfil", TypeAcreditationOptions.MENU);
        addOptions("USUARIOS", "Administrar Usuarios", "/usuario", TypeAcreditationOptions.MENU);
        addOptions("WEB_DESIGNER", "Web Designer", "/webdesigner", TypeAcreditationOptions.MENU);
        addOptions("KANBAN_BOARD", "Kanban Board", "/kanban", TypeAcreditationOptions.MENU);
        addOptions("SWAGGER_UI", "Swagger UI", "/swagger-ui", TypeAcreditationOptions.MENU);

        // Credentials Category (secured/restricted)
        addOptions("CREDENTIAL", "Credential Page", "/credential", TypeAcreditationOptions.MENU);
        addOptions("PERMISSION", "Options Page", "/permission", TypeAcreditationOptions.MENU);
        addOptions("ROLE", "Role Page", "/role", TypeAcreditationOptions.MENU);
        addOptions("DEPARTMENT", "Department Page", "/department", TypeAcreditationOptions.MENU);
        addOptions("USER", "User Page", "/user", TypeAcreditationOptions.MENU);

        // Rules Category
        addOptions("REGLAS", "Reglas Page", "/reglas", TypeAcreditationOptions.MENU);
        addOptions("REGLAS_VIEW", "Reglas View Crud Page", "/reglasview", TypeAcreditationOptions.MENU);

        // Crud Category
        addOptions("PERSONA", "Persona CRUD", "/persona", TypeAcreditationOptions.MENU);
        addOptions("PAIS", "Pais CRUD", "/pais", TypeAcreditationOptions.MENU);
        addOptions("DEPORTE", "Deporte CRUD", "/deporte", TypeAcreditationOptions.MENU);

        // CrudView Category
        addOptions("GRUPO", "Grupo CRUD", "/grupo", TypeAcreditationOptions.MENU);
        addOptions("SUBGRUPO", "SubGrupo CRUD", "/subgrupo", TypeAcreditationOptions.MENU);
        addOptions("PLANETA", "Planeta CRUD", "/planeta", TypeAcreditationOptions.MENU);
        addOptions("DATATABLE_EDITABLE_CRUD", "Datatable Editable Crud View", "/datatableeditablecrudview", TypeAcreditationOptions.MENU);
        addOptions("CANCION", "Cancion CRUD", "/cancion", TypeAcreditationOptions.MENU);

        // Library Category
        addOptions("AUTHOR", "Author Library", "/author", TypeAcreditationOptions.MENU);
        addOptions("BOOK", "Book Library", "/book", TypeAcreditationOptions.MENU);
        addOptions("PUBLISHER", "Publisher Library", "/publisher", TypeAcreditationOptions.MENU);
        addOptions("READER", "Reader Library", "/reader", TypeAcreditationOptions.MENU);

        // DataTable Category
        addOptions("DATATABLE", "DataTable Page", "/datatable", TypeAcreditationOptions.MENU);
        addOptions("DATATABLE_EDITABLE", "Datatable Editable", "/datatableeditable", TypeAcreditationOptions.MENU);

        // Master-Details Category
        addOptions("VIEW_DATATABLE", "View DataTable", "/viewdatatable", TypeAcreditationOptions.MENU);
    }

    private static void addOptions(String name, String desc, String path,TypeAcreditationOptions typeAcreditationOptions) {
        db.add(new AccreditationOption(UUID.nameUUIDFromBytes((name ).getBytes()), name , desc , path,typeAcreditationOptions));
        
    }

    public static List<AccreditationOption> findAll() {
        return new ArrayList<>(db);
    }

    public static void save(AccreditationOption record) {
        if (record.id() == null) {
            record = new AccreditationOption(UUID.randomUUID(), record.name(), record.description(), record.resourcePath(),record.typeAcreditationOptions());
        }
        delete(record.id().toString());
        db.add(record);
    }

    public static void delete(String id) {
        db.removeIf(r -> r.id().toString().equals(id));
    }

    public static Optional<AccreditationOption> findById(String id) {
        return db.stream().filter(r -> r.id().toString().equals(id)).findFirst();
    }
}
