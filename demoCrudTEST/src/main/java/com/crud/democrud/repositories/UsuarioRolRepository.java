package com.crud.democrud.repositories;


import com.crud.democrud.models.UsuarioModel;
import com.crud.democrud.models.UsuarioRolModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UsuarioRolRepository extends CrudRepository<UsuarioRolModel, Integer> {
    public abstract ArrayList<UsuarioRolModel> findByRol(Integer idrol);
}
