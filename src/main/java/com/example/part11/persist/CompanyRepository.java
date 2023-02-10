package com.example.part11.persist;

import com.example.part11.persist.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    boolean existsByTicker(String ticker);
    Optional<CompanyEntity> findByName(String name);
    Optional<CompanyEntity> findByTicker(String ticker);

//    Page<CompanyEntity> findByNameStartingWithIgnoreCase(String s, Pageable pageable);
}
