/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto_Capacitacion.demo.VentasController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ULISES
 */
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
public class VentaDTO {

    @JsonProperty("id")
    private Long id;

    private String folio;
    private double total;
    private String estado;
    private List<PartidaDTO> partida = new ArrayList<>();

    VentaDTO() {
    }
    
    public VentaDTO(VentaDTO entity) {
        this.id = entity.getId();
        this.total = entity.getTotal();
        this.estado = entity.getEstado();
        this.partida = entity.getPartida();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public List<PartidaDTO> getPartida() {
        return partida;
    }

    public void setPartida(List<PartidaDTO> partida) {
        this.partida = partida;
    }
    
    public void addPartida(PartidaDTO partida) {
        this.partida.add(partida);
    }

    @Override
    public String toString() {
        return "VentaDTO{" + "id=" + id + ", folio=" + folio + ", total=" + total + ", estado=" + estado + ", partida=" + partida + '}';
    }
}