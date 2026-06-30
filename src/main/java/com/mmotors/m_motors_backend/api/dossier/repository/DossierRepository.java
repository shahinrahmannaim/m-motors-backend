package com.mmotors.m_motors_backend.api.dossier.repository;

import com.mmotors.m_motors_backend.api.dossier.entity.Dossier;
import com.mmotors.m_motors_backend.api.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface DossierRepository extends JpaRepository<Dossier, Long>, JpaSpecificationExecutor<Dossier> {
      @EntityGraph(attributePaths = {"user","vehicle"})
      List<Dossier> findByUserOrderByCreatedDateDesc(User user);

      @EntityGraph(attributePaths = {"user","vehicle"})
      Optional<Dossier> findWithUserAndVehicleById(Long id);

      @Override
      @EntityGraph(attributePaths = {"user","vehicle"})
      List<Dossier> findAll();

      @Override
      @EntityGraph(attributePaths = {"user","vehicle"})
      Page<Dossier> findAll(Specification<Dossier> specification, Pageable pageable);

}