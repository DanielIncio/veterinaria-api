package com.veterinaria.veterinaria_api.repository;

import com.veterinaria.veterinaria_api.model.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DuenoRepository extends JpaRepository<Dueno, Long>{
    
}
