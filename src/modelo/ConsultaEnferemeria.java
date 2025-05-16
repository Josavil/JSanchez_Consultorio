/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author josavi
 */
public class ConsultaEnferemeria {
    
    String dniPaciente;
    Date fechaConsulta;
    double maxima, minima;
    int glucosa;
    double peso;
    int codigoFacultativo;    

    public ConsultaEnferemeria(String dniPaciente, Date fechaConsulta, double maxima, double minima, int glucosa, double peso, int codigoFacultativo) {
        this.dniPaciente = dniPaciente;
        this.fechaConsulta = fechaConsulta;
        this.maxima = maxima;
        this.minima = minima;
        this.glucosa = glucosa;
        this.peso = peso;
        this.codigoFacultativo = codigoFacultativo;
    }

    public String getDniPaciente() {
        return dniPaciente;
    }

    public void setDniPaciente(String dniPaciente) {
        this.dniPaciente = dniPaciente;
    }

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public double getMaxima() {
        return maxima;
    }

    public void setMaxima(double maxima) {
        this.maxima = maxima;
    }

    public double getMinima() {
        return minima;
    }

    public void setMinima(double minima) {
        this.minima = minima;
    }

    public int getGlucosa() {
        return glucosa;
    }

    public void setGlucosa(int glucosa) {
        this.glucosa = glucosa;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getCodigoFacultativo() {
        return codigoFacultativo;
    }

    public void setCodigoFacultativo(int codigoFacultativo) {
        this.codigoFacultativo = codigoFacultativo;
    }
    
    
    
    
    
    
    
    
}
