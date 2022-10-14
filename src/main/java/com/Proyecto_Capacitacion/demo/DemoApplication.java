package com.Proyecto_Capacitacion.demo;

import com.Proyecto_Capacitacion.demo.VentasController.AgregarVentaInterfaz;
import com.Proyecto_Capacitacion.demo.VentasController.CatalogoPartidasInterfaz;
import com.Proyecto_Capacitacion.demo.VentasController.CatalogoVentasInterfaz;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        AgregarVentaInterfaz manejoVentaInterfaz = new AgregarVentaInterfaz();
        manejoVentaInterfaz.setVisible(true);

//        CatalogoVentasInterfaz catalogoVentasInterfaz = new CatalogoVentasInterfaz();
//        catalogoVentasInterfaz.setVisible(true);
//
//        CatalogoPartidasInterfaz catalogoPartidasInterfaz = new CatalogoPartidasInterfaz();
//        catalogoPartidasInterfaz.setVisible(true);

        SpringApplication.run(DemoApplication.class, args);
    }

}
