package com.Proyecto_Capacitacion.demo;

import com.Proyecto_Capacitacion.demo.VentasController.AgregarVentaInterfaz;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        AgregarVentaInterfaz VentaInterfaz = new AgregarVentaInterfaz();
        VentaInterfaz.setVisible(true);

        SpringApplication.run(DemoApplication.class, args);
    }

}
