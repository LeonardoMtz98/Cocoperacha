/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "transaccion")
@NamedQueries({
    @NamedQuery(name = "Transaccion.findAll", query = "SELECT t FROM Transaccion t")})
public class Transaccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pktransaccion")
    private Integer pktransaccion;
    @Column(name = "Elegido")
    private Integer elegido;
    @Column(name = "megusta")
    private Boolean megusta;
    @Lob
    @Size(max = 65535)
    @Column(name = "comentario")
    private String comentario;
    @JoinColumn(name = "fkenvio", referencedColumnName = "pkenvio")
    @ManyToOne(optional = false)
    private Envio fkenvio;
    @JoinColumn(name = "fkusuariosolicitante", referencedColumnName = "correo")
    @ManyToOne(optional = false)
    private Usuario fkusuariosolicitante;
    @JoinColumn(name = "fkproducto", referencedColumnName = "pkproducto")
    @ManyToOne(optional = false)
    private Producto fkproducto;

    public Transaccion() {
    }

    public Transaccion(Integer pktransaccion) {
        this.pktransaccion = pktransaccion;
    }

    public Integer getPktransaccion() {
        return pktransaccion;
    }

    public void setPktransaccion(Integer pktransaccion) {
        this.pktransaccion = pktransaccion;
    }

    public Integer getElegido() {
        return elegido;
    }

    public void setElegido(Integer elegido) {
        this.elegido = elegido;
    }

    public Boolean getMegusta() {
        return megusta;
    }

    public void setMegusta(Boolean megusta) {
        this.megusta = megusta;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Envio getFkenvio() {
        return fkenvio;
    }

    public void setFkenvio(Envio fkenvio) {
        this.fkenvio = fkenvio;
    }

    public Usuario getFkusuariosolicitante() {
        return fkusuariosolicitante;
    }

    public void setFkusuariosolicitante(Usuario fkusuariosolicitante) {
        this.fkusuariosolicitante = fkusuariosolicitante;
    }

    public Producto getFkproducto() {
        return fkproducto;
    }

    public void setFkproducto(Producto fkproducto) {
        this.fkproducto = fkproducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pktransaccion != null ? pktransaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaccion)) {
            return false;
        }
        Transaccion other = (Transaccion) object;
        if ((this.pktransaccion == null && other.pktransaccion != null) || (this.pktransaccion != null && !this.pktransaccion.equals(other.pktransaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Transaccion[ pktransaccion=" + pktransaccion + " ]";
    }
    
}
