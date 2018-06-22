/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entities.Transaccion;
import Entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Query;

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

    public String retorno(String cad) {
        return "Esta es tu cadena: " + cad;
    }
    
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
    
    public Usuario getUsuarioLoggeado() {
        return usuarioLogeado;
    }
    
    public boolean isSesionIniciada() {
        return sesionIniciada;
    }
    
    public String logIn() {
        Iterator<Usuario> itrUsuarios = getFachada().findAll().iterator();
        while (itrUsuarios.hasNext()) {
            Usuario temp = itrUsuarios.next();
            if (temp.getCorreo().equals(usuario.getCorreo())) {
                if (temp.getPassword().equals(usuario.getPassword())) {
                    usuarioLogeado = temp;
                    usuario = null;
                    sesionIniciada = true;     
                    return null;
                }
            }            
        }    
        return "index.html";
    }
    
    public String logOut() {
        sesionIniciada = false;
        usuarioLogeado = null;
        return "index.html";
    }
    
    public void crearUsuario() {
        getFachada().create(usuario);
    }
    
    public List<Usuario> getUsuarios() {
        return getFachada().findAll();
    }
    
    public List<Usuario> getTopUsuarios() {
        List<Usuario> usuariosTop = new ArrayList<>();
        usuariosTop.add(new Usuario());
        usuariosTop.add(new Usuario());
        usuariosTop.add(new Usuario());
        long top1 = 0, top2 = 0, top3 = 0;
        List<Usuario> usuarios = getUsuarios();
        usuarios.forEach(us -> {
            Query consultaTrans = getFachada().getEntityManager().createQuery("SELECT COUNT(a) FROM Transaccion a WHERE a.fkproducto.fkusuario.correo = :us AND a.elegido = 1");
            consultaTrans.setParameter("us", us.getCorreo());
            long cantidadTrans = (long)consultaTrans.getSingleResult();
            if (cantidadTrans > top1) {
                usuariosTop.set(2, usuariosTop.get(1));
                usuariosTop.set(1, usuariosTop.get(0));
                usuariosTop.set(0, us);
            }else if (cantidadTrans > top2) {
                usuariosTop.set(2, usuariosTop.get(1));
                usuariosTop.set(1, us);
            }else if (cantidadTrans > top3) {
                usuariosTop.set(2, us);
            }
        });
        return usuariosTop;
    }

}
