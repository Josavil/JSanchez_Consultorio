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
public class Consulta {
    
    private String dniPaciente;
    private Date fechaConsulta;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;
    private int codigoFacultativo;    

    public Consulta(String dniPaciente, Date fechaConsulta, String diagnostico, String tratamiento, String observaciones, int codigoFacultativo) {
        this.dniPaciente = dniPaciente;
        this.fechaConsulta = fechaConsulta;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
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

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getCodigoFacultativo() {
        return codigoFacultativo;
    }

    public void setCodigoFacultativo(int codigoFacultativo) {
        this.codigoFacultativo = codigoFacultativo;
    }
    
    
}
