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
        return "index";
    }
    
    public String logOut() {
        sesionIniciada = false;
        usuarioLogeado = null;
        return "index?faces-redirect=true";
    }
    
    public void crearUsuario() {
        getFachada().create(usuario);
    }
    
    public List<Usuario> getUsuarios() {
        return getFachada().findAll();
    }
    
    public List<Usuario> getTopUsuarios() {
        List<Usuario> usuariosTop = new ArrayList<>();
        List<Usuario> listaFinal = new ArrayList<>();
        long cantidad = 0;
        
        Query consultaNum = getFachada().getEntityManager().createQuery("SELECT COUNT(a) FROM Usuario a");
        cantidad = (long)consultaNum.getSingleResult();
        
        int cant = Integer.parseInt(String.valueOf(cantidad));
        int tamanio = 10;
        
        long[] top = new long[cant];
        
        for(int y=0; y<cantidad; y++) usuariosTop.add(new Usuario());
        
        if(cant > 10) tamanio = 10;
        else tamanio = cant;
        
        for(int y=0; y<10; y++) listaFinal.add(new Usuario());
        
        List<Usuario> usuarios = getUsuarios();
        usuarios.forEach(us ->{
            Query consultaTrans = getFachada().getEntityManager().createQuery("SELECT COUNT(a) FROM Transaccion a WHERE a.fkproducto.fkusuario.correo = :us AND a.elegido = 1");
            consultaTrans.setParameter("us", us.getCorreo());
            long cantidadTrans = (long)consultaTrans.getSingleResult();
            
            for(int x=0; x<usuariosTop.size(); x++){
                if(top[x] == 0){
                    if(cantidadTrans != 0){
                        usuariosTop.set(x, us);
                        top[x] = cantidadTrans;
                    }
                    break;
                }
            }
        });
        
        for(int i = 0; i < top.length - 1; i++){
            for(int j = 0; j < top.length - 1; j++){
                if (top[j] < top[j + 1]){
                    long tmp = top[j+1];
                    top[j+1] = top[j];
                    top[j] = tmp;
                    Usuario tempo = usuariosTop.get(j+1);
                    usuariosTop.set(j+1, usuariosTop.get(j)); 
                    usuariosTop.set(j, tempo);
                }
            }
        }
        
        for(int i = 0; i < tamanio; i++){
            listaFinal.set(i, usuariosTop.get(i));
        }
        
        return listaFinal;
    }

}
