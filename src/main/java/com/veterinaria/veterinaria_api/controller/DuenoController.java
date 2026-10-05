package com.veterinaria.veterinaria_api.controller;

import com.veterinaria.veterinaria_api.model.Dueno;
import com.veterinaria.veterinaria_api.service.DuenoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/duenos")
public class DuenoController {
    
    @Autowired 
    private DuenoService duenoService;
    
    @PostMapping
    public Dueno crear (@RequestBody Dueno duenoNuevo){
        return duenoService.crear(duenoNuevo);
    }
    
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        duenoService.eliminar(id);
    }
    
    @PutMapping("/{id}")
    public Dueno actualizarDueno(@PathVariable Long id ,@RequestBody Dueno duenoActualizado){
        return duenoService.actualizarDueno(id, duenoActualizado);
    }
    
    @GetMapping
    public List<Dueno> listar(){
        return duenoService.listar();
    }
    
    @GetMapping("/{id}")
    public Dueno buscarDueno(@PathVariable Long id){
        return duenoService.buscarPorId(id);
    }
}

