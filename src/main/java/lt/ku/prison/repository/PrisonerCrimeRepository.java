package lt.ku.prison.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import lt.ku.prison.classes.PrisonerCrimeKey;
import lt.ku.prison.entities.PrisonerCrime;

public interface PrisonerCrimeRepository  extends JpaRepository<PrisonerCrime, PrisonerCrimeKey>{
	List<PrisonerCrime> findPrisonerCrimesByPrisonerId(Integer id);
	
	List<PrisonerCrime> findPrisonerCrimesByCrimeId(Integer id);
	
	List<PrisonerCrime> findByIdDate(LocalDate date);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM PrisonerCrime PR WHERE PR.id.prisonerId = :prisonerId AND PR.id.crimeId = :crimeId AND PR.id.date = :date")
	void deletePrisonerCrime(Integer prisonerId, Integer crimeId, String date);
	
	

}
