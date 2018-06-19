/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entities.Categoria;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Leonardo Martinez
 */
@Named("controladorCategoria")
@SessionScoped
public class ControladorCategoria implements Serializable{
    private Categoria categoria;
    @EJB
    private FachadaCategoria fachada;
    
    public ControladorCategoria() {
        
    }
    
    public FachadaCategoria getFachada() {
        return fachada;
    }
    
    public Categoria getCategoria() {
        if (categoria == null) {
            categoria = new Categoria();
        }
        return categoria;
    }
    
    public void crearCategoria() {
        getFachada().create(categoria);
    }
    
    public List<Categoria> getCategorias() {
        return getFachada().findAll();
    }
}
