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
 * @author ULISES
 */
public class ModificarVenta extends AgregarVentaInterfaz {

    String linkVentas = "http://localhost:8080/venta";
    String linkPartidas = "http://localhost:8080/partida";

    public void ModificarVenta(String folio, String estado, DefaultTableModel tabla, Long idVenta, List<Long> idPartida, List<Long> idPartidaBorrar) {

        //Insert usando entidades
        try {
            RestTemplate restTemplate = new RestTemplate();

            VentaDTO venta = new VentaDTO();
            venta.setId(idVenta);
            venta.setFolio(folio);
            venta.setEstado(estado);

            AgregarPartidas(venta, idPartida, restTemplate, tabla);

            EliminarPartidas(venta, idPartida, restTemplate, idPartidaBorrar);

        } catch (Exception e) {
            System.out.println("Error al agregar - " + e);
        }
    }

    public void AgregarPartidas(VentaDTO venta, List<Long> idPartida, RestTemplate restTemplate, DefaultTableModel tabla) {

        List<PartidaDTO> lista_partidas = new ArrayList<>();

        for (int i = 0; i < tabla.getRowCount(); i++) {

            PartidaDTO partida = new PartidaDTO();

            if (idPartida.size() > i) {
                partida.setId(idPartida.get(i));
            }

            partida.setArticulo(tabla.getValueAt(i, 0).toString());
            partida.setCantidad(Double.parseDouble(tabla.getValueAt(i, 1).toString()));
            partida.setPrecio(Double.parseDouble(tabla.getValueAt(i, 2).toString()));
            partida.setEstado(tabla.getValueAt(i, 3).toString());

            lista_partidas.add(partida);

            //Agregar partidas a la venta
            venta.setPartida(lista_partidas);
        }

        restTemplate.postForEntity(linkVentas, venta, String.class);

    }

    public void EliminarPartidas(VentaDTO venta, List<Long> idPartida, RestTemplate restTemplate, List<Long> idPartidaBorrar) {

        for (int i = 0; i < idPartidaBorrar.size(); i++) {
            System.out.println("borrarPartida - " + idPartidaBorrar.get(i));
            String borrarPartida = linkPartidas + "/" + idPartidaBorrar.get(i);
            restTemplate.delete(borrarPartida);
        }
        idPartida.clear();
        idPartidaBorrar.clear();
        
    }

}
