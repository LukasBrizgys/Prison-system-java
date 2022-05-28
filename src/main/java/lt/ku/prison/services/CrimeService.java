package lt.ku.prison.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.ku.prison.entities.Crime;
import lt.ku.prison.repository.CrimeRepository;

@Service
public class CrimeService {
	@Autowired
	CrimeRepository crimeRepository;
	
	public List<Crime> getAllCrimes(){
		return crimeRepository.findAll();
	}
	public Crime getCrime(Integer id) {
		return crimeRepository.getReferenceById(id);
	}
	public Crime addCrime(Crime crime) {
		return crimeRepository.save(crime);
	}
	public void deleteCrime(Integer id) {
		crimeRepository.deleteById(id);
	}
	public Crime updateCrime(Crime crime) {
		Crime old = crimeRepository.getReferenceById(crime.getId());
		old.setName(crime.getName());
		crimeRepository.save(old);
		return old;
	}
}
