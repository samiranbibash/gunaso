package com.envisionnepal.gunasho.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariableEntityRepository extends JpaRepository<VariableEntity,Long> {
}
