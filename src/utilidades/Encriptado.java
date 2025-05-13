/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Clase que proporciona métodos para encriptar y desencriptar cadenas de texto
 * utilizando diferentes algoritmos de cifrado.
 *
 * Esta clase ofrece funcionalidades para:
 * Codificación y decodificación Base64
 * Encriptación y desencriptación AES (Advanced Encryption Standard)
 * 
 *
 * @author josavi
 */
public class Encriptado {

    /**
     *
     * @param cadenaEncriptar
     * @return
     */
    public static String codificaBASE64(String cadenaEncriptar) {
        String textoCodificado = Base64.getEncoder().encodeToString(cadenaEncriptar.getBytes());
        return textoCodificado;

    }

    /**
     *
     * @param cadenaEncriptada
     * @return
     */
    public static String decodificarBASE64(String cadenaEncriptada) {
        String cadenaDesencriptada = null;
        try {

            byte[] decode = Base64.getDecoder().decode(cadenaEncriptada.getBytes());
            cadenaDesencriptada = new String(decode, "utf8");

            return cadenaDesencriptada;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cadenaDesencriptada;
    }

    private static final String MILLAVE = "vJMnURwFuojTiaJT"; //16 bytes (16 caracteres) = 128 bits (opción mas robustica)

    /**
     *
     * @param campoOriginal
     * @return
     */
    public static String encriptar(String campoOriginal) {
        String encriptado = null;

        try {
            Key millaveEnBytes = new SecretKeySpec(MILLAVE.getBytes(), "AES");

            Cipher encriptador = Cipher.getInstance("AES");
            encriptador.init(Cipher.ENCRYPT_MODE, millaveEnBytes);

            byte[] bytesEncriptados = encriptador.doFinal(campoOriginal.getBytes());

            encriptado = org.apache.commons.codec.binary.Base64.encodeBase64String(bytesEncriptados);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encriptado;
    }

    /**
     *
     * @param campoEncriptado
     * @return
     */
    public static String desencriptar(String campoEncriptado) {
        String desencriptado = null;

        try {

            byte[] bytesEncriptados = org.apache.commons.codec.binary.Base64.decodeBase64(campoEncriptado);

            Key millaveEnBytes = new SecretKeySpec(MILLAVE.getBytes(), "AES");

            Cipher encriptador = Cipher.getInstance("AES");
            encriptador.init(Cipher.DECRYPT_MODE, millaveEnBytes);

            desencriptado = new String(encriptador.doFinal(bytesEncriptados));

            return desencriptado;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return desencriptado;
    }
}
