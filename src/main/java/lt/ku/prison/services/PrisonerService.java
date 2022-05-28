package lt.ku.prison.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.ku.prison.entities.Prisoner;
import lt.ku.prison.repository.PrisonerRepository;

@Service
public class PrisonerService {
	@Autowired
	PrisonerRepository prisonerRepository;
	
	public List<Prisoner> getAllPrisoners(){
		return prisonerRepository.findAll();
	}
	
	public Prisoner getPrisoner(Integer id) {
		return prisonerRepository.getReferenceById(id);
	}
	public Prisoner addPrisoner(Prisoner prisoner) {
		return prisonerRepository.save(prisoner);
	}
	public void deletePrisoner(Integer id) {
		prisonerRepository.deleteById(id);
	}
	public Prisoner updatePrisoner(Prisoner prisoner) {
		Prisoner old = prisonerRepository.getReferenceById(prisoner.getId());
		old.setName(prisoner.getName());
		old.setSurname(prisoner.getSurname());
		old.setBirthDate(prisoner.getBirthDate());
		old.setPhone(prisoner.getPhone());
		old.setCity(prisoner.getCity());
		if(prisoner.getFileName() != null) {
			old.setFileName(prisoner.getFileName());
		}
		old.setStatus(prisoner.getStatus());
		prisonerRepository.save(old);
		return old;
	}
	
}
