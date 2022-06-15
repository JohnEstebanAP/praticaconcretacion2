package com.crud.democrud.services.iface;

import com.crud.democrud.models.UsuarioModel;
import com.crud.democrud.models.UsuarioRolModel;
import com.crud.democrud.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface UsuarioRolService {

    void create(UsuarioRolModel usuarioRol);
    
    List<UsuarioRolModel> getAll();

    Optional<UsuarioRolModel> getById(Integer id);

    void update(int id, UsuarioRolModel rol);
    
    void delete(int id);
}
