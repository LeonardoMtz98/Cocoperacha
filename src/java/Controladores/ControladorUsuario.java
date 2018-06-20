/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entities.Usuario;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Leonardo Martinez
 */
@Named("controladorUsuario")
@SessionScoped
public class ControladorUsuario implements Serializable{
    private Usuario usuario;
    private Usuario usuarioLogeado;
    private boolean sesionIniciada = false;
    
    @EJB
    private FachadaUsuario fachada;

    public ControladorUsuario() {
    }
    
    public FachadaUsuario getFachada() {
        return fachada;
    }
    
    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }
    
    public boolean isSesionIniciada() {
        return sesionIniciada;
    }
    
    public boolean logIn() {
        Iterator<Usuario> itrUsuarios = getFachada().findAll().iterator();
        while (itrUsuarios.hasNext()) {
            Usuario temp = itrUsuarios.next();
            if (temp.getCorreo().equals(usuario.getCorreo())) {
                if (temp.getPassword().equals(usuario.getPassword())) {
                    usuarioLogeado = temp;
                    usuario = null;
                    sesionIniciada = true;
                    return true;
                }
            }
        }
        return false;
    }
    
    public void crearUsuario() {
        getFachada().create(usuario);
    }
    
    public List<Usuario> getUsuarios() {
        return getFachada().findAll();
    }
}
