package com.crud.democrud.services;

import com.crud.democrud.models.UsuarioModel;
import com.crud.democrud.models.UsuarioRolModel;
import com.crud.democrud.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    public UsuarioModel guardarUsuario(UsuarioModel usuario){
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioModel> obtenerPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public Optional<UsuarioModel> obtenerPorEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public ArrayList<UsuarioModel>  obtenerPorPrioridad(Integer prioridad) {
        return usuarioRepository.findByPrioridad(prioridad);
    }

    public boolean eliminarUsuario(Long id) {
        try{
            usuarioRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    public boolean eliminarPorEmail(String email) {
        try{
            Optional<UsuarioModel> usuario = usuarioRepository.findByEmail(email);
            Long id = usuario.get().getId();
            eliminarUsuario(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }


    public void update(Long id, UsuarioModel usuario) {
        Optional<UsuarioModel> existsUsuario = usuarioRepository.findById(id);
        if (existsUsuario.isPresent()) {
            existsUsuario.get().setId(usuario.getId());
            existsUsuario.get().setNombre(usuario.getNombre());
            existsUsuario.get().setEmail(usuario.getEmail());
            existsUsuario.get().setPrioridad(usuario.getPrioridad());
            usuarioRepository.save(existsUsuario.get());
        }
    }
    
}