package com.ensolution.ensol.repository.app.jpa.facility;

import com.ensolution.ensol.entity.app.facility.StackInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StackInformationRepository extends JpaRepository<StackInformation, Integer> {
}
