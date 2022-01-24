package gr.sae.repository;

import gr.sae.domain.Occupation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccupationRepository extends JpaRepository<Occupation, Long> {
}
