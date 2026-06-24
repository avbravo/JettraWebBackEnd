package com.jettra.plugin.acreditation.repository;

import com.jettra.jwt.enumerations.TypeFeatureResource;
import com.jettra.plugin.acreditation.entity.Feature;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FeatureRepository {
    private static final List<Feature> db = new ArrayList<>();

    static {
        // Navigation / Administration / Credentials (secured/restricted)
        addOptions("PANEL_PRINCIPAL", "Panel Principal", "/dashboard", TypeFeatureResource.MENU);
        addOptions("PERMISOS", "Administrar Permisos", "/permiso", TypeFeatureResource.MENU);
        addOptions("ROLES", "Administrar Roles", "/rol", TypeFeatureResource.MENU);
        addOptions("PERFILES", "Administrar Perfiles", "/perfil", TypeFeatureResource.MENU);
        addOptions("USUARIOS", "Administrar Usuarios", "/usuario", TypeFeatureResource.MENU);
        addOptions("WEB_DESIGNER", "Web Designer", "/webdesigner", TypeFeatureResource.MENU);
        addOptions("KANBAN_BOARD", "Kanban Board", "/kanban", TypeFeatureResource.MENU);
        addOptions("SWAGGER_UI", "Swagger UI", "/swagger-ui", TypeFeatureResource.MENU);

        // Credentials Category (secured/restricted)
        addOptions("CREDENTIAL", "Credential Page", "/credential", TypeFeatureResource.MENU);
        addOptions("PERMISSION", "Options Page", "/permission", TypeFeatureResource.MENU);
        addOptions("ROLE", "Role Page", "/role", TypeFeatureResource.MENU);
        addOptions("DEPARTMENT", "Department Page", "/department", TypeFeatureResource.MENU);
        addOptions("USER", "User Page", "/user", TypeFeatureResource.MENU);

        // Rules Category
        addOptions("REGLAS", "Reglas Page", "/reglas", TypeFeatureResource.MENU);
        addOptions("REGLAS_VIEW", "Reglas View Crud Page", "/reglasview", TypeFeatureResource.MENU);

        // Crud Category
        addOptions("PERSONA", "Persona CRUD", "/persona", TypeFeatureResource.MENU);
        addOptions("PAIS", "Pais CRUD", "/pais", TypeFeatureResource.MENU);
        addOptions("DEPORTE", "Deporte CRUD", "/deporte", TypeFeatureResource.MENU);

        // CrudView Category
        addOptions("GRUPO", "Grupo CRUD", "/grupo", TypeFeatureResource.MENU);
        addOptions("SUBGRUPO", "SubGrupo CRUD", "/subgrupo", TypeFeatureResource.MENU);
        addOptions("PLANETA", "Planeta CRUD", "/planeta", TypeFeatureResource.MENU);
        addOptions("DATATABLE_EDITABLE_CRUD", "Datatable Editable Crud View", "/datatableeditablecrudview", TypeFeatureResource.MENU);
        addOptions("CANCION", "Cancion CRUD", "/cancion", TypeFeatureResource.MENU);

        // Library Category
        addOptions("AUTHOR", "Author Library", "/author", TypeFeatureResource.MENU);
        addOptions("BOOK", "Book Library", "/book", TypeFeatureResource.MENU);
        addOptions("PUBLISHER", "Publisher Library", "/publisher", TypeFeatureResource.MENU);
        addOptions("READER", "Reader Library", "/reader", TypeFeatureResource.MENU);

        // DataTable Category
        addOptions("DATATABLE", "DataTable Page", "/datatable", TypeFeatureResource.MENU);
        addOptions("DATATABLE_EDITABLE", "Datatable Editable", "/datatableeditable", TypeFeatureResource.MENU);

        // Master-Details Category
        addOptions("VIEW_DATATABLE", "View DataTable", "/viewdatatable", TypeFeatureResource.MENU);
    }

    private static void addOptions(String name, String desc, String path,TypeFeatureResource typeFeatureResource) {
        db.add(new Feature(UUID.nameUUIDFromBytes((name ).getBytes()), name , desc , path,typeFeatureResource));
        
    }

    public static List<Feature> findAll() {
        return new ArrayList<>(db);
    }

    public static void save(Feature record) {
        if (record.id() == null) {
            record = new Feature(UUID.randomUUID(), record.name(), record.description(), record.resourcePath(),record.typeFeatureResource());
        }
        delete(record.id().toString());
        db.add(record);
    }

    public static void delete(String id) {
        db.removeIf(r -> r.id().toString().equals(id));
    }

    public static Optional<Feature> findById(String id) {
        return db.stream().filter(r -> r.id().toString().equals(id)).findFirst();
    }
}
