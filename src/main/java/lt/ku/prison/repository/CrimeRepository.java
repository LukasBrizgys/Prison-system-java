package lt.ku.prison.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ku.prison.entities.Crime;

public interface CrimeRepository extends JpaRepository<Crime, Integer>{

}
