/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Clase con validaciones y otras funcionalidades necesarias para el correcto
 * funcionamiento de la aplicación Campos vacios
 *
 *
 * @author josavi
 */
public class Utilidades {

    public static boolean compruebaButtonRadios(ButtonGroup buttonGroups) {
        //          for (ButtonGroup b : panel.getButtonGroups()) {
        if (buttonGroups.getSelection() == null) {
            JOptionPane.showMessageDialog(null, "Debe rellenar el cuestionario.");
            return false;
        }
        return true;
    }

    public static boolean compruebaCamposVacios(JPanel panel) {

        for (Component c : panel.getComponents()) {

            //Si no pones esto no se mete en el JtextArea, el jScrollPane cómo que lo esconde
            if (c instanceof JScrollPane jScrollPane) {
                c = jScrollPane.getViewport().getView();
            }

            if (c instanceof JTextArea jtextArea) {
                if (jtextArea.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El campo " + c.getName() + " es obligatiorio.");
                    c.setBackground(Color.red);
                    return false;
                }
            }
            if (c instanceof JPasswordField jpasswordField) {
                String pass = new String(jpasswordField.getPassword());
                if (pass.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El campo " + c.getName() + " es obligatiorio.");
                    c.setBackground(Color.red);
                    return false;
                }
            }

            if (c instanceof JTextField jTextField) {
                if (jTextField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El campo " + c.getName() + " es obligatiorio.");
                    c.setBackground(Color.red);
                    return false;
                }

            }
            if (c instanceof JComboBox jComboBox) {
                if (jComboBox.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "El campo " + c.getName() + " es obligatorio.");
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean compruebaNumeroEntero(JTextField campo) {
        try {
            Integer.valueOf(campo.getText());
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El campo " + campo.getName() + " debe ser numérico.");
            return false;
        }
    }

    public static boolean compruebaNumeroTelefono(JTextField campo) {

        if (compruebaNumeroEntero(campo)) {
            if (campo.getText().length() == 9) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "El numero de telefono debe tener 9 dígitos.");
                return false;
            }
        }
        return false;
    }

    public static boolean compruebaNumeroColegiado(JTextField campo) {

        if (compruebaNumeroEntero(campo)) {
            if (campo.getText().length() == 9) {
                if (Integer.parseInt(campo.getText()) > 99999999) {
                    return true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "El numero de colegiado debe tener 9 dígitos y el primero no puede ser 0.");
                return false;
            }
        }
        return false;
    }

    public static boolean validarDNI(String dni) {
        if (dni.length() != 9) {
            return false;
        }
        String numeros = dni.substring(0, 8);
        if (!numeros.matches("\\d{8}")) {
            return false;
        }
        char letra = dni.charAt(8);
        if (!Character.isLetter(letra)) {
            return false;
        }
        int numero = Integer.parseInt(numeros);
        char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        int resto = numero % 23;
        return Character.toUpperCase(letra) == letras[resto];
    }

    public static boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        // Basic email validation pattern
        String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        return email.matches(pattern);
    }

    public static void reseteaFormulario(JPanel panel) {
        for (Component c : panel.getComponents()) {
            if (c instanceof JScrollPane jScrollPane) {
                c = jScrollPane.getViewport().getView();
            }
            if (c instanceof JTextField jTextField) {
                jTextField.setText("");
                c.setBackground(Color.WHITE);

            }

            if (c instanceof JPasswordField jPasswordField) {
                jPasswordField.setText("");
            }

            if (c instanceof JTextArea jTextArea) {
                jTextArea.setText("");
            }
//            if (c instanceof JLabel jLabel && c.getName()!=null  && c.getName().contains("NUM")) {
//                    jLabel.setText("Nº de caracteres");      
//            }
            if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            }
        }
    }

}
