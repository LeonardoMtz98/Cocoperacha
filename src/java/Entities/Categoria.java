/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "categoria")
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")})
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pkcategoria")
    private Integer pkcategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "buscado")
    private Integer buscado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkcategoria")
    private List<Producto> productoList;

    public Categoria() {
    }

    public Categoria(Integer pkcategoria) {
        this.pkcategoria = pkcategoria;
    }

    public Categoria(Integer pkcategoria, String nombre) {
        this.pkcategoria = pkcategoria;
        this.nombre = nombre;
    }

    public Integer getPkcategoria() {
        return pkcategoria;
    }

    public void setPkcategoria(Integer pkcategoria) {
        this.pkcategoria = pkcategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getBuscado() {
        return buscado;
    }

    public void setBuscado(Integer buscado) {
        this.buscado = buscado;
    }
    
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkcategoria != null ? pkcategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.pkcategoria == null && other.pkcategoria != null) || (this.pkcategoria != null && !this.pkcategoria.equals(other.pkcategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Categoria[ pkcategoria=" + pkcategoria + " ]";
    }
    
}
