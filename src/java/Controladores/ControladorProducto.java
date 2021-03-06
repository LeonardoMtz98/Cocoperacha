
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Entities.Categoria;
import Entities.Producto;
import Entities.Usuario;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Query;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;



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
    private int pkCategoriaProd;
    private Part imagen;
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

    public String setProductoPropio(Producto prod) {
        producto = prod;
        return "faces/listaInteresados.xhtml";
    }
    
    public Part getImagen() {
        return imagen;
    }

    public void setImagen(Part imagen) {
        this.imagen = imagen;
    }
    
    public int getCategoria() {
        return pkCategoriaProd;
    }
    public void setCategoria(int pkCategoria) {
        pkCategoriaProd = pkCategoria;
        producto.setFkcategoria(getFachada().getEntityManager().find(Categoria.class, pkCategoria));
    }
    
    public void crearProducto() {
        try {
            InputStream is = imagen.getInputStream();            
            producto.setImagen(IOUtils.toByteArray(is));
        } catch (IOException ex) {
            System.out.println("Error al obtener stream de imagen");
        }
        getFachada().create(producto);
        producto = null;
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
    
    public List<Producto> getMisProductos(Usuario usuario){
        Query consulta = getFachada().getEntityManager().createQuery("SELECT a FROM Producto a WHERE a.fkusuario = :usuario");
        consulta.setParameter("usuario", usuario);
        return consulta.getResultList();
    }

}
