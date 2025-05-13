/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author josavi
 */
public class Personal {

    private int numero_colegiado;
    private String nombre;
    private String apellidos;
    private int telefono;
    private String usuario;
    private String contrasenya;
    private String tipo;    

    /**
     * Constructor vacio
     */
    public Personal() {
    }
    
    /**
     * Constructor de personal con los datos necesarios en el Login
     * @param numero_colegiado
     * @param nombre
     * @param apellidos
     * @param tipo
     */
    public Personal(int numero_colegiado, String nombre, String apellidos, String tipo) {
        this.numero_colegiado = numero_colegiado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tipo = tipo;
    }

    /**
     * Constructor para registrar a un nuevo Personal
     * @param numero_colegiado
     * @param nombre
     * @param apellidos
     * @param telefono
     * @param usuario
     * @param contrasenya
     * @param tipo
     */
    public Personal(int numero_colegiado, String nombre, String apellidos, int telefono, String usuario, String contrasenya, String tipo) {
        this.numero_colegiado = numero_colegiado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.usuario = usuario;
        this.contrasenya = contrasenya;
        this.tipo = tipo;
    }
    
    /**
     *
     * @return
     */
    public int getNumero_colegiado() {
        return numero_colegiado;
    }

    /**
     *
     * @param numero_colegiado
     */
    public void setNumero_colegiado(int numero_colegiado) {
        this.numero_colegiado = numero_colegiado;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     *
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     *
     * @return
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     *
     * @param telefono
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     *
     * @return
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     *
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     *
     * @return
     */
    public String getContrasenya() {
        return contrasenya;
    }

    /**
     *
     * @param contrasenya
     */
    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    /**
     *
     * @return
     */
    public String getTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
}
