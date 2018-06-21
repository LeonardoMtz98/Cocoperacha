/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

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
import javax.persistence.Query;
/**
 *
 * @author Leonardo Martinez
 */
@Named("controladorTransaccion")
@SessionScoped
public class ControladorTransaccion implements Serializable{
    private Transaccion transaccion;
    private int productoId;
    @EJB
    private FachadaTransaccion fachada;
    
    public ControladorTransaccion() {
        
    }
    
    public FachadaTransaccion getFachada() {
        return fachada;
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
    
    public void crearTransaccion() {
        getFachada().create(transaccion);
    }
    
    public List<Transaccion> getTransaccions() {
        return getFachada().findAll();
    }

    public List<Transaccion> getTransaccionesCompletas() {
        List<Transaccion> resultado = new ArrayList<>();
        getTransaccions().forEach(trans -> {
            if (trans.getElegido() == Boolean.TRUE) {
                resultado.add(trans);
            }
        });
        return resultado;
    }
    
    public void setProductoId(int productoId){
        this.productoId=productoId;
    }
    
    public List<Transaccion> ListaInteresados(){
       
    List<Transaccion> listaDeInteresados = new ArrayList<>();
    int producto;
    String correo;
    List<Transaccion> transacciones = getFachada().findAll();    
    for(int i=0;i<transacciones.size();i++){
    producto=transacciones.get(i).getFkproducto().getPkproducto();
        if(producto==this.productoId)
        listaDeInteresados.add(transacciones.get(i));
    }
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
        Query consulta = getFachada().getEntityManager().createQuery("SELECT a FROM Transaccion a WHERE a.fkproducto.fkusuario =:usuario AND a.elegido = 1");
        consulta.setParameter("usuario", usuario);
        List<Transaccion> transacciones = consulta.getResultList();
        return transacciones;
    }
    
    public void guardarTransaccion() {
        getFachada().edit(transaccion);
    }
}
