/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto_Capacitacion.demo.VentasController;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author TESTER
 */
public class AgregarVenta extends AgregarVentaInterfaz {

    String linkVentas = "http://localhost:8080/venta";

    public boolean AggregarVenta(String folio, String estado, DefaultTableModel tabla) {

        //Insert usando entidades
        try {

            RestTemplate restTemplate = new RestTemplate();

            VentaDTO venta = new VentaDTO();
            venta.setFolio(folio);
            venta.setEstado(estado);

            List<PartidaDTO> lista_partidas = new ArrayList<>();

            for (int i = 0; i < tabla.getRowCount(); i++) {

                PartidaDTO partida = new PartidaDTO();
                partida.setArticulo(tabla.getValueAt(i, 0).toString());
                partida.setCantidad(Double.parseDouble(tabla.getValueAt(i, 1).toString()));
                partida.setPrecio(Double.parseDouble(tabla.getValueAt(i, 2).toString()));
                partida.setEstado(tabla.getValueAt(i, 3).toString());

                lista_partidas.add(partida);

                //Agregar partidas a la venta
                venta.setPartida(lista_partidas);

            }

            //Agregar ventas, post entity
            if (restTemplate.postForEntity(linkVentas, venta, String.class).getBody().equals("true")) {
                return true;
            }
            return false;

        } catch (Exception e) {
            System.out.println("Error al agregar - " + e);
        }

        return false;
    }

}
