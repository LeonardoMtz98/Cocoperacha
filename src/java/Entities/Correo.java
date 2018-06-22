/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author monyg
 */
public class Correo {
    private String UsuarioCorreo;
    private String Contrasenia;
    private String destino;
    private String Asunto;
    private String Mensaje;
    /**
     * @return the UsuarioCorreo
     */
    public String getUsuarioCorreo() {
        return UsuarioCorreo;
    }

    /**
     * @param UsuarioCorreo the UsuarioCorreo to set
     */
    public void setUsuarioCorreo(String UsuarioCorreo) {
        this.UsuarioCorreo = UsuarioCorreo;
    }

    /**
     * @return the Contrasenia
     */
    public String getContrasenia() {
        return Contrasenia;
    }

    /**
     * @param Contrasenia the Contrasenia to set
     */
    public void setContrasenia(String Contrasenia) {
        this.Contrasenia = Contrasenia;
    }

    /**
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return the Asunto
     */
    public String getAsunto() {
        return Asunto;
    }

    /**
     * @param Asunto the Asunto to set
     */
    public void setAsunto(String Asunto) {
        this.Asunto = Asunto;
    }

    /**
     * @return the Mensaje
     */
    public String getMensaje() {
        return Mensaje;
    }

    /**
     * @param Mensaje the Mensaje to set
     */
    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }
    
}
