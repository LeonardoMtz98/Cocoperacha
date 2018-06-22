/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entities.Envio;
import Entities.Producto;
import Entities.Transaccion;
import Entities.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Iterator;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.persistence.Query;
/**
 *
 * @author Leonardo Martinez
 */
@Named("controladorTransaccion")
@SessionScoped
public class ControladorTransaccion implements Serializable{
    private Transaccion transaccion;
    private int pkEnvioTrans;
    private int transACalificar;
    private int Ganador;
    @EJB
    @SessionScoped
    private FachadaTransaccion fachada;
    
    public ControladorTransaccion() {
        
    }
    
    public FachadaTransaccion getFachada() {
        return fachada;
    }
    public String goToCorreo(Transaccion trans) {
        transaccion = trans;
        return "faces/adminMail.xhtml";
    }
    public Transaccion getTransaccion() {
        if (transaccion == null) {
            transaccion = new Transaccion();
        }
        return transaccion;
    }
    
    public void setTransaccion(Transaccion trans){
        transaccion = trans;
    }

    public int getEnvioTrans() {
        return pkEnvioTrans;
    }

    public void setEnvioTrans(int envioTrans) {
        this.pkEnvioTrans = envioTrans;
    }
    
    public void crearTransaccion(Producto producto, Usuario usuario) {
        getTransaccion();
        transaccion.setFkproducto(producto);
        transaccion.setFkusuariosolicitante(usuario);
        transaccion.setFkenvio(getFachada().getEntityManager().find(Envio.class, pkEnvioTrans));
        getFachada().create(transaccion);
    }
    
    public List<Transaccion> getTransaccions() {
        Query consulta = getFachada().getEntityManager().createQuery("SELECT a FROM Transaccion a WHERE a.pktransaccion > 0");
        return consulta.getResultList();
    }
    
    public List<Transaccion> getMisTransacciones(Usuario usuario) {
        return getFachada().findAll();
    }
    
    public List<Transaccion> getTransaccionesCompletas() {
        List<Transaccion> resultado = new ArrayList<>();
        getTransaccions().forEach(trans -> {
            if (trans.getElegido() != null) {
                if (trans.getElegido() == 1 ) {
                resultado.add(trans);
                }
            }            
        });
        return resultado;
    }

    public int getTransACalificar() {
        transaccion = getFachada().find(transACalificar);
        return transACalificar;
    }

    public void setTransACalificar(int transACalificar) {
        transaccion = getFachada().find(transACalificar);
        this.transACalificar = transACalificar;
    }
    
    
    
    public List<Transaccion> ListaInteresados(Producto prod){
       
    List<Transaccion> listaDeInteresados = new ArrayList<>();
    List<Transaccion> transacciones = getFachada().findAll();    
    transacciones.forEach(trans -> {
        if (trans.getFkproducto().equals(prod)) {
            listaDeInteresados.add(trans);
        }
    });
    return listaDeInteresados;
    }
    
    public int getCantidadTransaccion(int pk){
        List<Integer> cantidad = new ArrayList<>();
        getTransaccionesCompletas().forEach(trans -> {
            if (trans.getFkproducto().getFkcategoria().getPkcategoria() == pk) {
                cantidad.add(1);
            }
        });
        return cantidad.size();
    }
    
    public void setElegido(Transaccion elegido) {
        transaccion = elegido;
    }
   
    public List<Transaccion> getTransaccionesGanadas(Usuario usuario) {
        Query consulta = getFachada().getEntityManager().createQuery("SELECT a FROM Transaccion a WHERE a.fkusuariosolicitante =:usuario AND a.elegido = 1");
        consulta.setParameter("usuario", usuario);
        List<Transaccion> transacciones = consulta.getResultList();
        return transacciones;
    }
    
    public void setGanador(int trans) {
        Ganador = trans;
        transaccion = getFachada().find(trans);
        transaccion.setElegido(1);
    }

    public int getGanador() {
        return Ganador;
    }
    
    public Usuario getDonado(Producto prod) {
        List<Usuario> res = new ArrayList<>();
        res.add(null);
        getTransaccions().forEach(trans -> {
            if (trans.getElegido() == 1) {
                res.add(0, trans.getFkusuariosolicitante());
            }
        });
        return res.get(0);
    }
    public void guardarTransaccion() {
        
        getFachada().edit(transaccion);
    }
    
    public void calificarTrans() {
        getFachada().edit(transaccion);
    }
}
