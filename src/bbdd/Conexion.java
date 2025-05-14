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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import modelo.Paciente;
import utilidades.Encriptado;

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
        String consulta = "SELECT CONCAT(nombre, ' ', apellidos) as nombre_completo, numero_colegiado, tipo AS TIPO FROM personal WHERE usuario=?";
        PreparedStatement pst;
        ResultSet rs;
        String[] userData = new String[3];

        try {
            pst = (PreparedStatement) conn.prepareStatement(consulta);
            pst.setString(1, user);
            rs = pst.executeQuery();

            if (rs.next()) {
                userData[0] = rs.getString("nombre_completo");
                userData[1] = rs.getString("numero_colegiado");
                userData[2] = rs.getString("TIPO");
            } else {
                userData[0] = "";
                userData[1] = "";
                userData[2] = "";
            }

            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            userData[0] = "";
            userData[1] = "";
            userData[2] = "";
        }

        return userData;  // Return the array regardless of what happened
    }

// debe mostrar Nombre dia y hora
    public static void tablaAgendaCitasMedico(DefaultTableModel modelo, String numeroProfesional) {
        Object[] datos = new Object[3];

        String consulta = "SELECT citas.nombre, citas.dia, citas.hora "
                + "FROM paciente "
                + "JOIN citas ON citas.dniPaciente = paciente.dni "
                + "JOIN consultas ON paciente.dni = consultas.dniPaciente "
                + "JOIN personal ON consultas.codigofacultativo = personal.numero_colegiado "
                + "WHERE citas.dia = CURDATE() "
                + "AND personal.numero_colegiado = ?;";

        try {
            PreparedStatement pst;
            ResultSet rs;

            pst = conn.prepareStatement(consulta);
            pst.setString(1, numeroProfesional);
            rs = pst.executeQuery();

            while (rs.next()) {
                datos[0] = Encriptado.desencriptar(rs.getString("NOMBRE"));
                datos[1] = rs.getString("DIA");
                datos[2] = rs.getString("HORA");

                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void tablaAgendaCitasEnfermeria(DefaultTableModel modelo, String numeroProfesional) {
        Object[] datos = new Object[3];

        String consulta = "SELECT citasEnfermeria.nombre AS NOMBRE, citasEnfermeria.dia AS DIA, citasEnfermeria.hora AS HORA "
                + "FROM paciente "
                + "JOIN citasEnfermeria ON citasEnfermeria.dniPaciente = paciente.dni "
                + "JOIN consultas ON paciente.dni = consultas.dniPaciente "
                + "JOIN personal ON consultas.codigofacultativo = personal.numero_colegiado "
                + "WHERE personal.tipo = 'MEDICO' "
                + "AND citasEnfermeria.dia = CURDATE() "
                + "AND personal.numero_colegiado = ?;";

        try {
            PreparedStatement pst;
            ResultSet rs;

            pst = conn.prepareStatement(consulta);
            pst.setString(1, numeroProfesional);
            rs = pst.executeQuery();

            while (rs.next()) {
                datos[0] = Encriptado.desencriptar(rs.getString("NOMBRE"));
                datos[1] = rs.getString("DIA");
                datos[2] = rs.getString("HORA");

                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean existePaciente(String dnipac) {

        try {
            String consulta = "SELECT * "
                    + "FROM paciente "
                    + "WHERE dni = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(consulta);
            pst.setString(1, dnipac);
            ResultSet rs = pst.executeQuery();

            return rs.next(); // Si existe devuelve TRue
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static String[] conseguirDatosPaciente(String dnipac) {
        String consulta = "SELECT paciente.nombre AS NOMBRE, paciente.apellidos AS APELLIDOS, paciente.telefono AS TELEFONO, paciente.email AS EMAIL "
                + "FROM paciente "
                + "WHERE dni = ?";
        PreparedStatement pst;
        ResultSet rs;
        String[] userData = new String[4];

        try {
            pst = (PreparedStatement) conn.prepareStatement(consulta);
            pst.setString(1, dnipac);
            rs = pst.executeQuery();

            if (rs.next()) {
                userData[0] = rs.getString("NOMBRE");
                userData[1] = rs.getString("APELLIDOS");
                userData[2] = rs.getString("TELEFONO");
                userData[3] = rs.getString("EMAIL");
            } else {
                userData[0] = "";
                userData[1] = "";
                userData[2] = "";
                userData[3] = "";
            }

            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            userData[0] = "";
            userData[1] = "";
            userData[2] = "";
            userData[3] = "";
        }

        return userData;  // Return the array regardless of what happened
    }

    public static void cargaComboCP(JComboBox combo) {

        try {
            String SSQL = "SELECT DISTINCT codigospostales.codigopostal AS CP "
                    + "FROM codigospostales ";

            com.mysql.jdbc.Statement st = (com.mysql.jdbc.Statement) conn.createStatement();
            ResultSet rs = st.executeQuery(SSQL);

            while (rs.next()) {
                combo.addItem(rs.getString("CP"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static boolean registrarPaciente(Paciente p) {
        try {
            String consulta = "INSERT INTO paciente (dni, nombre, apellidos,"
                    + " fechaNacimiento, telefono, email, cp, sexo, tabaquismo,"
                    + " consumoAlcohol, antecedentesSalud, datosSaludGeneral, fechaRegistro) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);"; //son 13
            
            PreparedStatement st;
            
            st = (PreparedStatement) conn.prepareStatement(consulta);
            
            st.setObject(1, p.getDni());
            st.setObject(2, p.getNombre());
            st.setObject(3, p.getApellidos());
            st.setObject(4, p.getFechaNacimiento());
            st.setObject(5, p.getTelefono());
            st.setObject(6, p.getEmail());
            st.setObject(7, p.getCp());
            st.setObject(8, p.getSexo());
            st.setObject(9, p.getTabaquismo());
            st.setObject(10, p.getConsumocalcohol());
            st.setObject(11, p.getAntecedentesSalud());
            st.setObject(12, p.getDatosSaludGeneral());
            st.setObject(13, p.getFechaRegistro());
            
            st.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
//
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
