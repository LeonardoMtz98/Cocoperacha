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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "producto")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pkproducto")
    private Integer pkproducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Lob
    @Column(name = "imagen")
    private byte[] imagen;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descripcionbreve")
    private String descripcionbreve;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkproducto")
    private List<Transaccion> transaccionList;
    @JoinColumn(name = "fkusuario", referencedColumnName = "correo")
    @ManyToOne(optional = false)
    private Usuario fkusuario;
    @JoinColumn(name = "fkcategoria", referencedColumnName = "pkcategoria")
    @ManyToOne(optional = false)
    private Categoria fkcategoria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isRegalado")
    boolean isRegalado;
    public Producto() {
    }

    public Producto(Integer pkproducto) {
        this.pkproducto = pkproducto;
    }

    public Producto(Integer pkproducto, String nombre, byte[] imagen, String descripcionbreve, String descripcion) {
        this.pkproducto = pkproducto;
        this.nombre = nombre;
        this.imagen = imagen;
        this.descripcionbreve = descripcionbreve;
        this.descripcion = descripcion;
    }

    public Integer getPkproducto() {
        return pkproducto;
    }

    public void setPkproducto(Integer pkproducto) {
        this.pkproducto = pkproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getDescripcionbreve() {
        return descripcionbreve;
    }

    public void setDescripcionbreve(String descripcionbreve) {
        this.descripcionbreve = descripcionbreve;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Transaccion> getTransaccionList() {
        return transaccionList;
    }

    public void setTransaccionList(List<Transaccion> transaccionList) {
        this.transaccionList = transaccionList;
    }

    public Usuario getFkusuario() {
        return fkusuario;
    }

    public void setFkusuario(Usuario fkusuario) {
        this.fkusuario = fkusuario;
    }

    public Categoria getFkcategoria() {
        return fkcategoria;
    }

    public void setFkcategoria(Categoria fkcategoria) {
        this.fkcategoria = fkcategoria;
    }

    public boolean isIsRegalado() {
        return isRegalado;
    }

    public void setIsRegalado(boolean isRegalado) {
        this.isRegalado = isRegalado;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkproducto != null ? pkproducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.pkproducto == null && other.pkproducto != null) || (this.pkproducto != null && !this.pkproducto.equals(other.pkproducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Producto[ pkproducto=" + pkproducto + " ]";
    }
    
}
