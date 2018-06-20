/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entities.Envio;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Leonardo Martinez
 */
@Named("controladorEnvio")
@SessionScoped
public class ControladorEnvio implements Serializable{
    private Envio envio;
    @EJB
    private FachadaEnvio fachada;
    
    public ControladorEnvio() {
        
    }
    
    public FachadaEnvio getFachada() {
        return fachada;
    }
    
    public Envio getEnvio() {
        if (envio == null) {
            envio = new Envio();
        }
        return envio;
    }
    
    public void crearEnvio() {
        getFachada().create(envio);
    }
    
    public List<Envio> getEnvios() {
        return getFachada().findAll();
    }
}
