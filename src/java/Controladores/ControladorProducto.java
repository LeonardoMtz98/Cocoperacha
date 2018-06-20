/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entities.Producto;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Leonardo Martinez
 */
@Named("controladorProducto")
@SessionScoped
public class ControladorProducto implements Serializable{
    private Producto producto;
    @EJB
    private FachadaProducto fachada;
    
    public ControladorProducto() {
        
    }
    
    public FachadaProducto getFachada() {
        return fachada;
    }
    
    public Producto getProducto() {
        if (producto == null) {
            producto = new Producto();
        }
        return producto;
    }
    
    public void crearProducto() {
        getFachada().create(producto);
    }
    
    public List<Producto> getProductos() {
        return getFachada().findAll();
    }
}
