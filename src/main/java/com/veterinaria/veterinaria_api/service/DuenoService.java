package com.veterinaria.veterinaria_api.service;

import com.veterinaria.veterinaria_api.model.Dueno;
import com.veterinaria.veterinaria_api.repository.DuenoRepository;
import com.veterinaria.veterinaria_api.repository.MascotaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DuenoService {
    @Autowired
    private DuenoRepository duenoRepository;
    @Autowired
    private MascotaRepository mascotaRepository;
    
    public Dueno crear(Dueno duenoNuevo){
        validarDueno(duenoNuevo);
        return duenoRepository.save(duenoNuevo);
    }
    
    public List<Dueno> listar(){
        return duenoRepository.findAll();
    }

    public void eliminar(Long id){
        if(!duenoRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No existe este Dueño");
        }
        if(mascotaRepository.existsByDuenoId(id)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"No se puede eliminar un Dueño con Mascotas registradas");
        }
        duenoRepository.deleteById(id);
    }
    
    public Dueno buscarPorId(Long id) {
    return duenoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el dueño"));
    }
    
    public Dueno actualizarDueno(Long id , Dueno duenoActualizado){
      Dueno duenoActual = buscarPorId(id);
      validarDueno(duenoActualizado);
      duenoActual.setNombre(duenoActualizado.getNombre());
      duenoActual.setTelefono(duenoActualizado.getTelefono());
      return duenoRepository.save(duenoActual);
    }
    
    public void validarDueno (Dueno dueno){
        if (dueno.getNombre()== null || dueno.getNombre().isEmpty()){
            throw new ResponseStatusException (HttpStatus.BAD_REQUEST,"Campo nombre vacio");
        }
        if (dueno.getTelefono()== null || dueno.getTelefono().isEmpty()){
            throw new ResponseStatusException (HttpStatus.BAD_REQUEST,"Campo telefono vacio");
        }
        
    }
}
