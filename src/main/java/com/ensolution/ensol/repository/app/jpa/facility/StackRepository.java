package com.ensolution.ensol.repository.app.jpa.facility;

import com.ensolution.ensol.entity.app.facility.Stack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StackRepository extends JpaRepository<Stack, Integer> {
  List<Stack> findByWorkplace_WorkplaceId(Integer workplaceId);
  Optional<Stack> findByStackName(String stackName);
}