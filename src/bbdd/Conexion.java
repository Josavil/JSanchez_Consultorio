/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 * En esta clase incluiremos todos los métodos para interactuar con la base de
 * datos, desde la conexión con la misma, el logado en la aplicación y todas las
 * consultas para insertar información y rescatarla desde la base de datos.
 *
 * @author josavi
 */
public class Conexion {

    /**
     *
     */
    public static java.sql.Connection conn; //Creamos un objeto de la clase connection

    /**
     *
     */
    public static void Conectar() {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://145.14.151.1/u812167471_consultorio25", "u812167471_consultorio25", "2025-Consultorio");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public static void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

//Usuarios ya creados:
//
//Perfil de Médico: medico / medico
//Perfil Enfermería: enfermera / enfermera
//Perfil administrador: admin / admin
    /**
     *
     * @param usu
     * @param pas
     * @return
     */
    public static boolean acceder(String usu, String pas) {

        String consulta
                = "SELECT usuario, contrasenya FROM personal WHERE usuario=? AND contrasenya=?";

        PreparedStatement pst;
        ResultSet rs;
        try {
            pst = (PreparedStatement) conn.prepareStatement(consulta);
            pst.setString(1, usu);
            pst.setString(2, pas);
            rs = pst.executeQuery();

            return rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Recupero los datos del usuario con el nombre de usuario cómo referencia
     * (son únicos) Recuperamos una cadena con DNI, nombre, apellidos, e numero
     * de colegiado,en un array String.
     *
     * @param user
     * @return
     */
public static String[] recuperaDatosUserLogado(String user) {
    String consulta = "SELECT CONCAT(nombre, ' ', apellidos) as nombre_completo, numero_colegiado FROM personal WHERE usuario=?";
    PreparedStatement pst;
    ResultSet rs;
    String[] userData = new String[2];
    
    try {
        pst = (PreparedStatement) conn.prepareStatement(consulta);
        pst.setString(1, user);  
        rs = pst.executeQuery();
        
        if (rs.next()) {
            userData[0] = rs.getString("nombre_completo");  
            userData[1] = rs.getString("numero_colegiado"); 
        } else {
            userData[0] = "";
            userData[1] = "";
        }
        
        rs.close();
        pst.close();
        
    } catch (SQLException ex) {
        Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        userData[0] = "";
        userData[1] = "";
    }
    
    return userData;  // Return the array regardless of what happened
}

//    public static String nombresito(String user, String pass) {
//
//        String consulta = "SELECT CONCAT (nombre, ' ', apellidos) AS NOMBRESITO FROM empleados WHERE usuario=? AND contraseña=?";
//        try {
//            PreparedStatement pst;
//            ResultSet rs;
//
//            pst = conn.prepareStatement(consulta);
//            pst.setString(1, user);
//            pst.setString(2, pass);
//            rs = pst.executeQuery();
//
//            if (rs.next()) {
//                return rs.getString("NOMBRESITO");
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ConsultasEmpleados.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return null;
//    }
//
//    public static boolean existeUsuario(String usu) {
//
//        try {
//            String consulta = "SELECT * FROM empleados WHERE usuario = ?";
//            java.sql.PreparedStatement pst = conn.prepareStatement(consulta);
//            pst.setString(1, usu);
//            ResultSet rs = pst.executeQuery();
//
//            return rs.next(); // Si existe devuelve TRue
//        } catch (SQLException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//
//    public static void cargaComboTurno(JComboBox combo) {
//
//        try {
//            String SSQL = "SELECT DISTINCT turno AS TURNO FROM empleados";
//
//            com.mysql.jdbc.Statement st = (com.mysql.jdbc.Statement) conn.createStatement();
//            ResultSet rs = st.executeQuery(SSQL);
//
//            while (rs.next()) {
//                combo.addItem(rs.getString("TURNO"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    public static void cargaComboAnioSalon(JComboBox combo) {
//
//        try {
//            String SSQL = "SELECT DISTINCT DATE(fecha) AS FECHA FROM reserva_salon";
//
//            com.mysql.jdbc.Statement st = (com.mysql.jdbc.Statement) conn.createStatement();
//            ResultSet rs = st.executeQuery(SSQL);
//
//            while (rs.next()) {
//                combo.addItem(rs.getString("FECHA"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    public static void cargaComboCaterin(JComboBox combo) {
//
//        try {
//            String SSQL = "SELECT DISTINCT caterin AS CATERIN FROM reserva_salon";
//
//            com.mysql.jdbc.Statement st = (com.mysql.jdbc.Statement) conn.createStatement();
//            ResultSet rs = st.executeQuery(SSQL);
//
//            while (rs.next()) {
//                combo.addItem(rs.getString("CATERIN"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    public static void cargaComboAnioHabitacion(JComboBox combo) {
//
//        try {
//            String SSQL = "SELECT DISTINCT DATE(fechaentrada) AS FECHA FROM reserva_habitacion";
//
//            com.mysql.jdbc.Statement st = (com.mysql.jdbc.Statement) conn.createStatement();
//            ResultSet rs = st.executeQuery(SSQL);
//
//            while (rs.next()) {
//                combo.addItem(rs.getString("FECHA"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
