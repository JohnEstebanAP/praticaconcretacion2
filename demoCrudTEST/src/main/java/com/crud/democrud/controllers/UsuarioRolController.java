package com.crud.democrud.controllers;

/*
        @ManyToOne Relation
        @OneToMany Relation
        @OneToOne Relation

* */
import com.crud.democrud.models.UsuarioModel;
import com.crud.democrud.models.UsuarioRolModel;
import com.crud.democrud.services.UsuarioRolServiceImpl;
import com.crud.democrud.services.iface.UsuarioRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/rol")
public class UsuarioRolController {

    @Autowired
    UsuarioRolServiceImpl usuarioRolService;

    @GetMapping
    public List<UsuarioRolModel> getAll(){
        return usuarioRolService.getAll();
    }


    @PostMapping
    public void create(@RequestBody UsuarioRolModel usuarioRol){
        usuarioRolService.create(usuarioRol);
    }

    @GetMapping(path = "/{idrol}")
    public Optional<UsuarioRolModel> getById(@PathVariable("idrol") Integer idrol){
        return this.usuarioRolService.getById(idrol);
    }

    @GetMapping("/query")
    public ArrayList<UsuarioRolModel> obtenerUsuarioPorPrioridad(@RequestParam("idrol") Integer idRol) {
        return this.usuarioRolService.getByRol(idRol);
    }

    @PutMapping("/{idrol}")
    public void update(@PathVariable Integer idrol, @RequestBody UsuarioRolModel usuarioRol){
        usuarioRolService.update(idrol, usuarioRol);
    }

    @DeleteMapping("/{idrol}")
    public void delete(@PathVariable Integer idrol){
        usuarioRolService.delete(idrol);
    }
}

/*
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "rol_usuario_id", nullable = false)
@Getter
@Setter
private UsuarioModel rolUser;
* */
