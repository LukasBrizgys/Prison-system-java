package lt.ku.prison.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.ku.prison.entities.PrisonerCrime;
import lt.ku.prison.repository.PrisonerCrimeRepository;

@Service
public class PrisonerCrimeService {
	@Autowired
	PrisonerCrimeRepository prisonerCrimeRepository;
	
	public List<PrisonerCrime> getAllCrimesByPrisonerId(Integer id){
		return prisonerCrimeRepository.findPrisonerCrimesByPrisonerId(id);
	}
	
	public List<PrisonerCrime> getAllCrimesByCrimeId(Integer id){
		return prisonerCrimeRepository.findPrisonerCrimesByCrimeId(id);
	}
	public PrisonerCrime addPrisonerCrime(PrisonerCrime prisonerCrime) {
		return prisonerCrimeRepository.save(prisonerCrime);
	}
	public void deletePrisonerCrime(Integer prisonerId, Integer crimeId, String date) {
		prisonerCrimeRepository.deletePrisonerCrime(prisonerId, crimeId, date);
	}
}
