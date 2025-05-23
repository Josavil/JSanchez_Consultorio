/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vistas;

import bbdd.Conexion;
import java.util.Date;
import modelo.Cita;
import utilidades.Encriptado;
import static vistas.ConsultaEnfermeria.datosPacientee;
import static vistas.ConsultaMedica.datosPaciente;
import static vistas.Login.dniPaciente;
import static vistas.MenuPrincipal.hoy;

/**
 *
 * @author josavi
 */
public class NuevaCitaEnfermeria extends javax.swing.JDialog {

    /**
     * Creates new form ConsultaMedica
     */
    public NuevaCitaEnfermeria(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        campoDni.setText(dniPaciente);
        campoNombreApellidos.setText(Encriptado.desencriptar(datosPacientee[0]) + Encriptado.desencriptar(datosPacientee[1]));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        campoDni = new javax.swing.JTextField();
        botonGuardar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        campoNombreApellidos = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboHora = new javax.swing.JComboBox<>();
        comboFecha = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(600, 465));
        setMinimumSize(new java.awt.Dimension(600, 465));
        setResizable(false);

        panel.setBackground(new java.awt.Color(0, 204, 204));

        jLabel3.setText("DNI PACIENTE");

        campoDni.setEnabled(false);

        botonGuardar.setText("GUARDAR");
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        jLabel8.setText("NOMBRE Y APELLIDOS");

        campoNombreApellidos.setEnabled(false);

        jLabel9.setText("HORA");

        jLabel10.setText("FECHA");

        comboHora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));
        comboHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboHoraActionPerformed(evt);
            }
        });

        comboFecha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Una semana", "Dos semanas", "Un mes" }));
        comboFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFechaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap(202, Short.MAX_VALUE)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboHora, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoDni)
                            .addComponent(campoNombreApellidos))))
                .addGap(61, 61, 61))
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(campoDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(campoNombreApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43))
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(comboFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(56, 56, 56)
                .addComponent(botonGuardar)
                .addGap(63, 63, 63))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/logo_good.png"))); // NOI18N

        jLabel2.setText("NUEVA CITA ENFERMERÍA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(38, 38, 38))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed

        //HACER LA CONSULTA DE GUARDAR LA CITA
        //
        //DNI PACIENTE Y NOMBRE Y APELLIDOS YA LO TENEMOS
        //LA FECHA DEBES PONER EL DIA DE HOY Y MODIFICARLA SEGUN LO QUE PONGA EN EL COMBO
        //LA HORA SE DEBE GUARDAR CÓMO UN int
        //
        //
        //
        if (utilidades.Utilidades.compruebaCamposVacios(panel)) {
//Una semana
//Dos semanas
//Un mes
            Date diaCita = new Date();
            if (comboFecha.getSelectedIndex() == 1) {
                diaCita = new Date(hoy.getTime() + 7 * 24 * 60 * 60 * 1000L);
            }
            if (comboFecha.getSelectedIndex() == 2) {
                diaCita = new Date(hoy.getTime() + 14 * 24 * 60 * 60 * 1000L);
            }
            if (comboFecha.getSelectedIndex() == 3) {
                diaCita = new Date(hoy.getTime() + 30 * 24 * 60 * 60 * 1000L);
            }

            String dniEn = Encriptado.encriptar(dniPaciente);
            String nom = datosPacientee[0];
            Date dia = diaCita;
            int hor = comboHora.getSelectedIndex();
            String horStrin = Integer.toString(hor);

            Cita ci = new Cita(dniEn, nom, dia, hor);

            Conexion.Conectar();
            Conexion.registrarCitaMedica(ci);
            Conexion.desconectar();

            String asunto = "Cita enfermería";
            String mensaje = "<h2>--------------Datos de la cita--------------</h2>\n"
                    + " <p>Dni del paciente: " + dniPaciente + " </p>\n"
                    + "        <p>Nombre: " + Encriptado.desencriptar(datosPacientee[0]) + Encriptado.desencriptar(datosPacientee[1]) + " </p>\n"
                    + "        <p>Día: " + dia.toString() + " </p>\n"
                    + "        <p>Hora: " + horStrin + " </p>";

            
            Conexion.Conectar();
            String destinatario = Conexion.correoPaciente(Encriptado.encriptar(dniPaciente));

            Conexion.enviar(asunto, mensaje, destinatario);
            Conexion.desconectar();
            
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void comboHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboHoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboHoraActionPerformed

    private void comboFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboFechaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NuevaCitaEnfermeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevaCitaEnfermeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevaCitaEnfermeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevaCitaEnfermeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NuevaCitaEnfermeria dialog = new NuevaCitaEnfermeria(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonGuardar;
    private javax.swing.JTextField campoDni;
    private javax.swing.JTextField campoNombreApellidos;
    private javax.swing.JComboBox<String> comboFecha;
    private javax.swing.JComboBox<String> comboHora;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
