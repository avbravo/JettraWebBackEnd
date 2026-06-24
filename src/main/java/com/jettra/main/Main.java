/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.jettra.main;

import com.jettra.main.jwt.AuthController;
import com.jettra.server.JettraServer;
import com.jettra.server.config.JettraConfigProperty;
import com.jettra.server.config.ConfigInjector;

import com.jettra.server.openapi.OpenApiHandler;
import com.jettra.server.openapi.SwaggerUIHandler;

import java.util.List;

public class Main {

    @JettraConfigProperty(name = "app.title")
    private String appTitle;
    @JettraConfigProperty(name = "server.port")
    private String port;
    @JettraConfigProperty(name = "server.contextpath")
    private String contextpath;

    public void initUI() {
        ConfigInjector.inject(this);
        System.out.println("Iniciando aplicación Web: " + appTitle);
    }

//    public static void main(String[] args) {
    void main() {
        Main app = new Main();
        app.initUI();

        // Configurar la ruta de redirección en ErrorPage, usando contextpath (y el puerto implícitamente por el host)
        io.jettra.wui.complex.ErrorPage.path = "http://localhost:" + app.port + app.contextpath;

        System.out.println("Levantando servidor de enrutamiento JettraServer empotrado...");
        JettraServer server = new JettraServer();
        server.setErrorPage("/error");
        server.addHandler("/error", io.jettra.wui.complex.ErrorPage.class);
        server.addHandler("/swagger-ui", io.jettra.wui.complex.SwaggerUIPage.class);


        List<Class<?>> controllers = List.of(
                //Autentification
                com.jettra.plugin.autentification.controller.AuthentificationController.class,
                com.jettra.plugin.autentification.controller.CredentialController.class,
                com.jettra.plugin.autentification.controller.RoleController.class,
                com.jettra.plugin.company.controller.DepartmentController.class,
                com.jettra.plugin.autentification.controller.UserController.class,
                //Accreditation
                com.jettra.plugin.acreditation.controller.FeatureController.class,
                com.jettra.plugin.acreditation.controller.PermissionController.class,
                com.jettra.plugin.acreditation.controller.PermitByDepartmentController.class,
                //Company
                com.jettra.plugin.company.controller.CompanyController.class,
                com.jettra.plugin.company.controller.DepartmentController.class,
                com.jettra.plugin.company.controller.HeadquartersController.class
                
        );

        // Exponer el JSON de OpenAPI
        server.addHandler("/openapi.json", new OpenApiHandler(controllers));

        // Exponer la interfaz Swagger UI
        server.addHandler("/swagger-ui", new SwaggerUIHandler("/openapi.json"));

        com.jettra.rest.server.JettraRestServer.register(server, AuthController.class);
//Autentification
        com.jettra.rest.server.JettraRestServer.register(server, com.jettra.plugin.autentification.controller.AuthentificationController.class);
        com.jettra.rest.server.JettraRestServer.register(server, com.jettra.plugin.autentification.controller.CredentialController.class);
        com.jettra.rest.server.JettraRestServer.register(server, com.jettra.plugin.autentification.controller.RoleController.class);
        com.jettra.rest.server.JettraRestServer.register(server, com.jettra.plugin.autentification.controller.UserController.class);
        //Acreditation
        com.jettra.rest.server.JettraRestServer.register(server, com.jettra.plugin.acreditation.controller.FeatureController.class);
        com.jettra.rest.server.JettraRestServer.register(server, com.jettra.plugin.acreditation.controller.PermissionController.class);
        com.jettra.rest.server.JettraRestServer.register(server, com.jettra.plugin.acreditation.controller.PermitByDepartmentController.class);
        //Company
        com.jettra.rest.server.JettraRestServer.register(server, com.jettra.plugin.company.controller.CompanyController.class);
        com.jettra.rest.server.JettraRestServer.register(server, com.jettra.plugin.company.controller.DepartmentController.class);
        com.jettra.rest.server.JettraRestServer.register(server, com.jettra.plugin.company.controller.HeadquartersController.class);

        server.start();
    }
}
