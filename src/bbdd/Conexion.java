/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cita;
import modelo.Consulta;
import modelo.ConsultaEnferemeria;
import modelo.Paciente;
import modelo.Personal;
import utilidades.Encriptado;
import utilidades.UtilidadEmail;

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

    public static void tablaMedicoConsultas(DefaultTableModel modelo, String dni) {
        Object[] datos = new Object[4];

        String consulta = "SELECT fechaConsulta AS FECHA, diagnostico AS DIAGNOSTICO, "
                + "tratamiento AS TRATAMIENTO, observaciones AS OBSERVACIONES "
                + "FROM consultas "
                + "WHERE dniPaciente = ? ;";

        try {
            PreparedStatement pst;
            ResultSet rs;

            pst = conn.prepareStatement(consulta);
            pst.setString(1, dni);
            rs = pst.executeQuery();
            modelo.setRowCount(0);

            while (rs.next()) {
                datos[0] = rs.getString("FECHA");
                datos[1] = rs.getString("DIAGNOSTICO");
                datos[2] = rs.getString("TRATAMIENTO");
                datos[3] = rs.getString("OBSERVACIONES");

                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void tablaEnfermeriaConsultas(DefaultTableModel modelo, String dni) {
        Object[] datos = new Object[5];

        String consulta = "SELECT fechaConsulta AS FECHA, tensionMax AS MAXIMA, "
                + "tensionMin AS MINIMA, glucosa AS GLUCOSA, peso AS PESO "
                + "FROM enfermeria "
                + "WHERE dniPaciente = ? ;";

        try {
            PreparedStatement pst;
            ResultSet rs;

            pst = conn.prepareStatement(consulta);
            pst.setString(1, dni);
            rs = pst.executeQuery();
            modelo.setRowCount(0);

            while (rs.next()) {
                datos[0] = rs.getString("FECHA");
                datos[1] = rs.getString("MAXIMA");
                datos[2] = rs.getString("MINIMA");
                datos[3] = rs.getString("GLUCOSA");
                datos[4] = rs.getString("PESO");

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
        }
        return false;

    }

    public static boolean existeUsuario(String usu) {

        try {
            String consulta = "SELECT * "
                    + "FROM personal "
                    + "WHERE usuario = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(consulta);
            pst.setString(1, usu);
            ResultSet rs = pst.executeQuery();

            return rs.next(); // Si existe devuelve TRue
        } catch (SQLException ex) {
        }
        return false;
    }

    public static boolean existeNumeroColegiado(String numcol) {

        try {
            String consulta = "SELECT * "
                    + "FROM personal "
                    + "WHERE numero_colegiado = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(consulta);
            pst.setString(1, numcol);
            ResultSet rs = pst.executeQuery();

            return rs.next(); // Si existe devuelve TRue
        } catch (SQLException ex) {
        }
        return false;
    }

    public static String[] conseguirDatosPaciente(String dnipac) {
        try {
            String consulta = "SELECT paciente.nombre AS NOMBRE, paciente.apellidos AS APELLIDOS, paciente.telefono AS TELEFONO, paciente.email AS EMAIL "
                    + "FROM paciente "
                    + "WHERE dni = ?";
            PreparedStatement pst;
            ResultSet rs;
            String[] userData = new String[4];

            pst = (PreparedStatement) conn.prepareStatement(consulta);
            pst.setString(1, dnipac);
            rs = pst.executeQuery();

            if (rs.next()) {
                userData[0] = rs.getString("NOMBRE");
                userData[1] = rs.getString("APELLIDOS");
                userData[2] = rs.getString("TELEFONO");
                userData[3] = rs.getString("EMAIL");
            }

            return userData;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void tablaInfoPacientes(DefaultTableModel modelo) {

        Object[] datos = new Object[5];

        String consulta = "SELECT paciente.dni AS DNI, paciente.nombre AS NOMBRE, paciente.apellidos AS APELLIDOS, "
                + "paciente.telefono AS TELEFONO, paciente.cp AS CP FROM paciente;";
        try {
            com.mysql.jdbc.Statement st = (com.mysql.jdbc.Statement) conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            modelo.setRowCount(0);
            while (rs.next()) {
                datos[0] = Encriptado.desencriptar(rs.getString("DNI"));
                datos[1] = Encriptado.desencriptar(rs.getString("NOMBRE"));
                datos[2] = Encriptado.desencriptar(rs.getString("APELLIDOS"));
                datos[3] = rs.getString("TELEFONO");
                datos[4] = rs.getString("CP");

                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    public static void cargaComboTipo(JComboBox combo) {

        try {
            String SSQL = "SELECT DISTINCT personal.tipo AS TIPOS "
                    + "FROM personal ";

            com.mysql.jdbc.Statement st = (com.mysql.jdbc.Statement) conn.createStatement();
            ResultSet rs = st.executeQuery(SSQL);

            while (rs.next()) {
                combo.addItem(rs.getString("TIPOS"));
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

    public static boolean actualizarPaciente(Paciente pi) {
        try {
            String consulta = "UPDATE paciente SET nombre =?, apellidos =?, "
                    + "telefono =?, cp =? "
                    + "WHERE dni = ?;";

            PreparedStatement st;

            st = (PreparedStatement) conn.prepareStatement(consulta);

            st.setObject(1, pi.getNombre());
            st.setObject(2, pi.getApellidos());
            st.setObject(3, pi.getTelefono());
            st.setObject(4, pi.getCp());
            st.setObject(5, pi.getDni());

            st.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean registrarPersonal(Personal pp) {
        try {
            String consulta = "INSERT INTO personal (numero_colegiado, nombre, apellidos,"
                    + " telefono, usuario, contrasenya, tipo) "
                    + "VALUES (?,?,?,?,?,?,?);"; //son 7

            PreparedStatement st;

            st = (PreparedStatement) conn.prepareStatement(consulta);

            st.setObject(1, pp.getNumero_colegiado());
            st.setObject(2, pp.getNombre());
            st.setObject(3, pp.getApellidos());
            st.setObject(4, pp.getTelefono());
            st.setObject(5, pp.getUsuario());
            st.setObject(6, pp.getContrasenya());
            st.setObject(7, pp.getTipo());

            st.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean registrarConsulta(Consulta c) {
        try {
            String consulta = "INSERT INTO consultas (dniPaciente, fechaConsulta, "
                    + "diagnostico, tratamiento, observaciones, codigofacultativo) "
                    + "VALUES (?,?,?,?,?,?);"; //son 6

            PreparedStatement st;

            st = (PreparedStatement) conn.prepareStatement(consulta);

            st.setObject(1, c.getDniPaciente());
            st.setObject(2, c.getFechaConsulta());
            st.setObject(3, c.getDiagnostico());
            st.setObject(4, c.getTratamiento());
            st.setObject(5, c.getObservaciones());
            st.setObject(6, c.getCodigoFacultativo());

            st.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean registrarConsultaEnfermeria(ConsultaEnferemeria ce) {
        try {
            String consulta = "INSERT INTO enfermeria (dniPaciente, fechaConsulta, tensionMax, tensionMin, glucosa, peso, codigoFacultativo) "
                    + "VALUES (?,?,?,?,?,?,?);"; //son 7

            PreparedStatement st;

            st = (PreparedStatement) conn.prepareStatement(consulta);

            st.setObject(1, ce.getDniPaciente());
            st.setObject(2, ce.getFechaConsulta());
            st.setObject(3, ce.getMaxima());
            st.setObject(4, ce.getMinima());
            st.setObject(5, ce.getGlucosa());
            st.setObject(6, ce.getPeso());
            st.setObject(7, ce.getCodigoFacultativo());

            st.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean registrarCitaMedica(Cita ci) {
        try {
            String consulta = "INSERT INTO citas (dniPaciente, nombre, dia, hora) "
                    + "VALUES (?,?,?,?);"; //son 4

            PreparedStatement st;

            st = (PreparedStatement) conn.prepareStatement(consulta);

            st.setObject(1, ci.getDniPaciente());
            st.setObject(2, ci.getNombre());
            st.setObject(3, ci.getDia());
            st.setObject(4, ci.getHora());

            st.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean registrarCitaEnfermeria(Cita ce) {
        try {
            String consulta = "INSERT INTO citasEnfermeria (dniPaciente, nombre, dia, hora) "
                    + "VALUES (?,?,?,?);"; //son 4

            PreparedStatement st;

            st = (PreparedStatement) conn.prepareStatement(consulta);

            st.setObject(1, ce.getDniPaciente());
            st.setObject(2, ce.getNombre());
            st.setObject(3, ce.getDia());
            st.setObject(4, ce.getHora());

            st.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void enviar(String asunto, String mensaje, String destinatario) {

        String asu = asunto;
        String msg = mensaje + "<p><img src=https://reynaldomd.com/firmacorreo/firmacorreo.png></p> "
                + "<p>Has recibido este email porque has solicitado una cita en el centro médico. Por favor, no responda a este correo electrónico: ha sido generado automáticamente.</p>";
        String des = destinatario;

        UtilidadEmail uee = new UtilidadEmail(asu, msg, des);

        if (uee.enviaMailHtml()) {
            JOptionPane.showMessageDialog(null, "Correo electrónico enviado correctamente", "Envio de correo electrónico", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(null, "Ha habido un error y no se ha enviado el correo correctamente", "Envio de correo", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static String correoPaciente(String dniUsuario) {
        String consulta = "SELECT email AS EMAIL FROM paciente WHERE dni = ?";
        PreparedStatement pst;
        ResultSet rs;
        String correo = "";

        try {
            pst = (PreparedStatement) conn.prepareStatement(consulta);
            pst.setString(1, dniUsuario);
            rs = pst.executeQuery();

            if (rs.next()) {
                correo = rs.getString("EMAIL");
            }

            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return correo;
    }

}
