/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto_Capacitacion.demo.VentasController;

//import com.Proyecto_Capacitacion.demo.VentasModelo.Partida;
//import com.Proyecto_Capacitacion.demo.VentasModelo.Venta;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author TESTER
 */
public class ConsultarVenta extends AgregarVentaInterfaz { // extends AgregarVentaInterfaz

    public String JsonVenta(String folio) {

        String linkVentas = "http://localhost:8080/ventas";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> ventasJsonEntero = restTemplate.getForEntity(linkVentas + "?folio=" + folio, String.class);

        String ventasJson = ventasJsonEntero.getBody();
        ventasJson = ventasJson.substring(1, ventasJson.length() - 1);

        return ventasJson;
    }

    public List<Object> MostrarVenta(String ventasJson, DefaultTableModel tabla) {

        //Mostrar la venta en la tabla
        ObjectMapper mapper = new ObjectMapper();
        BigDecimal total = BigDecimal.valueOf(0);
        String estadoVenta = "";

        try {

            VentaDTO ventasValues = mapper.readValue(ventasJson, VentaDTO.class);
//            Venta ventasValues = mapper.readValue(ventasJson, Venta.class);

            //Estado venta
            estadoVenta = ventasValues.getEstado();
            
            idVenta = ventasValues.getId();

            ventasValues.getPartida();
            Iterator<PartidaDTO> iterator = ventasValues.getPartida().iterator();
//            Iterator<Partida> iterator = ventasValues.getPartida().iterator();

            
            int cont = 0;

            idPartida.clear();

            while (iterator.hasNext()) {
                String[] partidasExistentes = ("" + iterator.next()).split(",");

                idPartida.add(Long.valueOf(partidasExistentes[0]));

                String articulo = partidasExistentes[1];
                String cantidad = partidasExistentes[2];
                String precio = partidasExistentes[3];
                String estado = partidasExistentes[4];
                BigDecimal importe = BigDecimal.valueOf(Double.parseDouble(cantidad)).multiply(BigDecimal.valueOf(Double.parseDouble(precio)));

                //Calcular total
                total = total.add(importe);

                tabla.addRow(new Object[]{articulo, cantidad, precio, estado, importe});

                cont++;
            }

        } catch (Exception e) {
            System.out.println("Error Mapper - " + e);
        }

        return Arrays.asList(total, idVenta, idPartida, estadoVenta);
    }
}
