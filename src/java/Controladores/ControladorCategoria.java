/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entities.Categoria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Query;

/**
 *
 * @author Leonardo Martinez
 */
@Named("controladorCategoria")
@SessionScoped
public class ControladorCategoria implements Serializable{
    private Categoria categoria;
    private int[] buscadas;
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
        List<Categoria> res = getFachada().findAll();
        res.add(0, new Categoria(0, "Todas"));
        return res;
    }
    
    public List<Categoria> getTopCategorias() {
        Query consulta = getFachada().getEntityManager().createQuery("Select a From Categoria a ORDER BY a.buscado DESC");
        return (List<Categoria>)consulta.setMaxResults(3).getResultList();
    }
    
   public int[] getBuscadas() {
        if (buscadas == null) 
        {
            buscadas = new int [getFachada().findAll().size()];
        }
        return buscadas;
    }
    public void setBuscadas(int[] buscadas) {
        this.buscadas = buscadas;
    }
    public void seBusca() {
        for (int i = 0; i < buscadas.length; i++) {
            Categoria temp = getFachada().find(buscadas[i]);
            temp.setBuscado(temp.getBuscado() + 1);
            getFachada().edit(temp);
        }
    }
    
    public Categoria getCategoriaPorId(int pk) {
        return getFachada().find(pk);
    }
}
