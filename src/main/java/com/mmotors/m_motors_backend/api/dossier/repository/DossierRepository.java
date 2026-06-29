package com.mmotors.m_motors_backend.api.dossier.repository;

import com.mmotors.m_motors_backend.api.dossier.entity.Dossier;
import com.mmotors.m_motors_backend.api.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DossierRepository extends JpaRepository<Dossier, Long> {
      @EntityGraph(attributePaths = {"user","vehicle"})
      List<Dossier> findByUserOrderByCreatedDateDesc(User user);

      @EntityGraph(attributePaths = {"user","vehicle"})
      Optional<Dossier> findWithUserAndVehicleById(Long id);

      @Override
      @EntityGraph(attributePaths = {"user","vehicle"})
      List<Dossier> findAll();

}