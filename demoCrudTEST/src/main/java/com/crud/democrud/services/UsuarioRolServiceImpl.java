package com.crud.democrud.services;

import com.crud.democrud.models.UsuarioModel;
import com.crud.democrud.models.UsuarioRolModel;
import com.crud.democrud.repositories.UsuarioRolRepository;
import com.crud.democrud.services.iface.UsuarioRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioRolServiceImpl implements UsuarioRolService {

    @Autowired
    private UsuarioRolRepository usuarioRolRepository;

    @Override
    public void create(UsuarioRolModel usuarioRol) {
        usuarioRolRepository.save(usuarioRol);
    }

    @Override
    public List<UsuarioRolModel> getAll() {
        List<UsuarioRolModel> roles = new ArrayList<>();
        usuarioRolRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Optional<UsuarioRolModel> getById(Integer id) {
        return usuarioRolRepository.findById(id);
    }

    public ArrayList<UsuarioRolModel>  getByRol(Integer idrol) {
        return usuarioRolRepository.findByRol(idrol);
    }

    @Override
    public void update(int id, UsuarioRolModel usuarioRol) {
        Optional<UsuarioRolModel> existsRol = usuarioRolRepository.findById(id);
        if (existsRol.isPresent()) {

            existsRol.get().setIdRol(usuarioRol.getIdRol());
            existsRol.get().setIdUsuario(usuarioRol.getIdUsuario());
            existsRol.get().setRol(usuarioRol.getRol());
            usuarioRolRepository.save(existsRol.get());
        }
    }

    @Override
    public void delete(int id) {
        Optional<UsuarioRolModel> existsRol = usuarioRolRepository.findById(id);
        if (existsRol.isPresent()) {
            try {
                usuarioRolRepository.delete(existsRol.get());
            }catch(Exception err){
                try {
                    usuarioRolRepository.deleteById(id);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
