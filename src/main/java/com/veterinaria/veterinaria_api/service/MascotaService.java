package com.veterinaria.veterinaria_api.service;

import com.veterinaria.veterinaria_api.model.Dueno;
import com.veterinaria.veterinaria_api.model.Mascota;
import com.veterinaria.veterinaria_api.repository.DuenoRepository;
import com.veterinaria.veterinaria_api.repository.MascotaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MascotaService {
    @Autowired
    private MascotaRepository mascotaRepository; 
    
    @Autowired
    private DuenoRepository duenoRepository;
      
    public Mascota crear(Mascota mascotaNueva, Long duenoId){
        validarMascota(mascotaNueva);
        Dueno duenoReal = duenoRepository.findById(duenoId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Dueno no existente"));
        mascotaNueva.setDueno(duenoReal);
        
        return mascotaRepository.save(mascotaNueva);
    }
    
    public List<Mascota> listar(){
        return mascotaRepository.findAll();
    }
    
    public void eliminar (Long id){
        if(!mascotaRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Mascota no registrada");
        }
        mascotaRepository.deleteById(id);
    }
    
    public Mascota buscarPorId(Long id){
        return mascotaRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Mascota no encontrada"));
    }
    
    public void validarMascota(Mascota mascota){
        if(mascota.getNombre()==null || mascota.getNombre().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Campo nombre vacio");
        if(mascota.getEspecie()==null || mascota.getEspecie().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Campo Especie vacio");
        if(mascota.getRaza()==null || mascota.getRaza().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Campo Raza vacio");
        if(mascota.getEdad()<0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Edad ingresasa no valida ");
        
    }
    
    public Mascota actualizar(Mascota mascotaActualizada, Long id){
        validarMascota(mascotaActualizada);
        Mascota mascotaExistente = buscarPorId(id);
        mascotaExistente.setNombre(mascotaActualizada.getNombre());
        mascotaExistente.setEspecie(mascotaActualizada.getEspecie());
        mascotaExistente.setRaza(mascotaActualizada.getRaza());
        mascotaExistente.setEdad(mascotaActualizada.getEdad());
        return mascotaRepository.save(mascotaExistente);
    }
}
