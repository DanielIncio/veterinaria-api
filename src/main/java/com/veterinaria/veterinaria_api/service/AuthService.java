
package com.veterinaria.veterinaria_api.service;

import com.veterinaria.veterinaria_api.model.Usuario;
import com.veterinaria.veterinaria_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired 
    private PasswordEncoder passwordEncoder;
    
    private void validarUsuario(Usuario usuario){
        if(usuario.getUsername() == null || usuario.getUsername().isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuario campo vacio");
        }
        if(usuario.getPassword() == null || usuario.getPassword().isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Password campo vacio");
        }
        if(usuario.getRol() == null || usuario.getRol().isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Rol campo vacio");
        }
    }
    
    public Usuario registrar(Usuario usuarioNuevo){
        validarUsuario(usuarioNuevo);
        if(usuarioRepository.existsByUsername(usuarioNuevo.getUsername())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Username existente");
        }
        
        String passwordHasheada = passwordEncoder.encode(usuarioNuevo.getPassword());
        usuarioNuevo.setPassword(passwordHasheada);       
        
        return usuarioRepository.save(usuarioNuevo);

    }
}
