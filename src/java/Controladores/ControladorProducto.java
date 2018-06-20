/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entities.Categoria;
import Entities.Producto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Query;

/**
 *
 * @author Leonardo Martinez
 */
@Named("controladorProducto")
@SessionScoped
public class ControladorProducto implements Serializable{
    private Producto producto;
    private String categoriaAMostrar = "Todas";
    @EJB
    private FachadaProducto fachada;
    
    public ControladorProducto() {
        
    }
    
    public FachadaProducto getFachada() {
        return fachada;
    }
    
    public void setCategoriaAMostrar(String categoria) {
        categoriaAMostrar = categoria;
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
        List<Producto> productos = getFachada().findAll();
        if (categoriaAMostrar.equals("Todas")) {
            return productos;
        } else {
            Query consultaPkCategoria = getFachada().getEntityManager().createQuery("Select a.pkcategoria From Categoria a Where a.nombre = :categoria");
            consultaPkCategoria.setParameter("categoria", categoriaAMostrar);
            int fkCategoria = (int)consultaPkCategoria.getSingleResult();
            
            Query consulta = getFachada().getEntityManager().createQuery("Select a From Producto a Where a.fkcategoria = :categoria");
            consulta.setParameter("categoria", fkCategoria);
            
            return (List<Producto>)consulta.getResultList();
        }
    }
}
