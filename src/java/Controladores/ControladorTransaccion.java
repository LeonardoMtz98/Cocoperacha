/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entities.Transaccion;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.util.ArrayList;
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
    
    public void crearTransaccion() {
        getFachada().create(transaccion);
    }
    
    public List<Transaccion> getTransaccions() {
        return getFachada().findAll();
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
        int cantidad = 6, numero = 0;
        
        /*ControladorProducto controladorProducto = new ControladorProducto();
        Iterator<Transaccion> itrTransaccion = getTransaccions().iterator();
        while (itrTransaccion.hasNext()) {
            Transaccion temp = itrTransaccion.next();
            numero = temp.getFkproducto().getPkproducto();
            if(temp.getElegido()){
                Producto producto = controladorProducto.getFachada().findAll().get(temp.getFkproducto().getPkproducto());
                if (producto.getFkcategoria().getPkcategoria() == pk) {
                    cantidad++;
                }
                //numero = producto.getFkcategoria().getPkcategoria();
                cantidad++;
            }
        }*/
        return cantidad;
    }
    
    public void setElegido(Transaccion elegido) {
        Transaccion miTransaccion = getFachada().find(elegido.getPktransaccion());
        miTransaccion.setElegido(Boolean.TRUE);
        getFachada().edit(miTransaccion);
    }
}
