/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.veterinaria.veterinaria_api.repository;

import com.veterinaria.veterinaria_api.model.Mascota;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Long>{
    List<Mascota> findByDuenoId(Long duenoId);
    boolean existsByDuenoId(Long duenoId);
}
