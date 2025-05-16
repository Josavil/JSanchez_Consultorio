/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * Clase que permite la creación y envio de correos electrónicos con datos
 * sacados de otras ventanas.
 *
 * @author josavi
 */
public class UtilidadEmail {

    private String asunto;
    private String mensaje;
    private String destinatario;
    private String ruta;

    public UtilidadEmail(String asunto, String mensaje, String destinatario, String ruta) {
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.destinatario = destinatario;
        this.ruta = ruta;
    }

    public UtilidadEmail(String asunto, String mensaje, String destinatario) {
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    public boolean enviaMailHtml() {
        try {
            HtmlEmail email = new HtmlEmail();

            //Parámetros de coneción con la cuenta  de correo
            email.setHostName("smtp.hostinger.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("noreply@reynaldomd.com", "2023-Online"));
            email.setSSLOnConnect(true);
            email.setCharset("UTF-8");
            email.setFrom("noreply@reynaldomd.com") //Montamos el mensaje
                    ;
            email.setSubject(this.asunto);
            email.setMsg(this.mensaje);
            email.addTo(this.destinatario);

            //Encviamos el correo
            email.send();
            return true;
        } catch (EmailException ex) {
            Logger.getLogger(UtilidadEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
}
