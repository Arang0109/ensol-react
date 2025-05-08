package com.ensolution.ensol.repository.app.jpa.organization;

import com.ensolution.ensol.entity.app.organization.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
}
