/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entities.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Stateless
public class FachadaProducto extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "CocoperachaPU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public FachadaProducto() {
        super(Producto.class);
    }
    
}
