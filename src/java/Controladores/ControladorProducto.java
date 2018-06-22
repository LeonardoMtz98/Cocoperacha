
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
import java.util.Base64;
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
    private int categoriaAMostrar = 0;
      private String usuario="";

    @EJB
    private FachadaProducto fachada;
    
    public ControladorProducto() {
        
    }
    
    public FachadaProducto getFachada() {
        return fachada;
    }
    
    public void setCategoriaAMostrar(int categoria) {
        categoriaAMostrar = categoria;
    }
    
    public Producto getProducto() {
        if (producto == null) {
            producto = new Producto();
        }
        return producto;
    }
    public String setProducto(Producto prod) {
        producto = prod;
        return "faces/detalleProducto.xhtml";
    }
    public void crearProducto() {
        getFachada().create(producto);
    }
    
    public List<Producto> getProductos() {
        List<Producto> productos = getFachada().findAll();
        List<Producto> resultado = new ArrayList<>();
        if (categoriaAMostrar == 0) {            
            productos.forEach(prod->{
                if (!prod.isIsRegalado()) {
                    resultado.add(prod);
                }
            });
        } else {
            productos.forEach(prod -> {
                if (prod.getFkcategoria().getPkcategoria() == categoriaAMostrar && !prod.isIsRegalado()) {
                    resultado.add(prod);
                }
            });
        }
        
        
        
        
        
        return resultado;
    }
    public Producto findProducto(int pk)
    {
        return getFachada().find(pk);
    }
    public String convertImg(int pk)
    {
        Producto producto =getFachada().find(pk);
        byte[] photo = producto.getImagen();
        String bphoto = Base64.getEncoder().encodeToString(photo);
        return bphoto;
    }


    public void setUsuario(String correo){
        this.usuario=correo;
    }
    
    public List<Producto> getMisProductos(){   
        List<Producto> misProductos = new ArrayList<>();
        String correo;
        List<Producto> productos = getFachada().findAll();    
        for(int i=0;i<productos.size();i++){
        correo=productos.get(i).getFkusuario().getCorreo();
            if(correo.equals(this.usuario))
            misProductos.add(productos.get(i));
        }

        return misProductos;
    }

}
