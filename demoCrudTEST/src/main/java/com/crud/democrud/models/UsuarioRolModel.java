package com.crud.democrud.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Entity
@Table(name = "usuariorol")
public class UsuarioRolModel {

    @Id
    @Column(name = "idrol")
    private Integer idRol;

    //@ManyToMany()

    @ManyToOne()
    @JoinColumn(name = "idusuario", referencedColumnName = "id")
    private UsuarioModel idUsuario;

    @Column(name = "rol")
    private String rol;

    public UsuarioRolModel(Integer idRol, UsuarioModel idUsuario, String rol) {
        this.idRol = idRol;
        this.idUsuario = idUsuario;
        this.rol = rol;
    }

    public UsuarioRolModel() {

    }

    public UsuarioModel getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioModel idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    
}
