package lt.ku.prison.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ku.prison.entities.Prisoner;

public interface PrisonerRepository extends JpaRepository<Prisoner, Integer>{

}
