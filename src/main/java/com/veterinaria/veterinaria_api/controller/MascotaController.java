
package com.veterinaria.veterinaria_api.controller;

import com.veterinaria.veterinaria_api.model.Mascota;
import com.veterinaria.veterinaria_api.service.MascotaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mascotas")


public class MascotaController {
    
    @Autowired 
    private MascotaService mascotaService;
    
    @PostMapping
    public Mascota crear(@RequestBody Mascota mascotaNueva ,@RequestParam Long duenoId){
        return mascotaService.crear(mascotaNueva, duenoId);
    }
    
    @GetMapping
    public List<Mascota> listar(){
        return mascotaService.listar();
    }
    
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        mascotaService.eliminar(id);
    }
    
    @GetMapping("/{id}")
    public Mascota buscarPorId(@PathVariable Long id){
        return mascotaService.buscarPorId(id);
    }
    
    @PutMapping("/{id}")
    public Mascota actualizarMascota (@PathVariable Long id ,@RequestBody Mascota mascotaActualizada){
        return mascotaService.actualizar(mascotaActualizada, id);
    }
    
}
