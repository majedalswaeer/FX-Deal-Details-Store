package com.ps.warehouse.repositories;

import com.ps.warehouse.entities.DealDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealDetailRepository extends JpaRepository<DealDetailEntity, String> {
}
