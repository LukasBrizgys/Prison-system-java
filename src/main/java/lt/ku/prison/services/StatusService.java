package lt.ku.prison.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.ku.prison.entities.Status;
import lt.ku.prison.repository.StatusRepository;

@Service
public class StatusService {
	@Autowired
	StatusRepository statusRepository;
	
	public List<Status> getAllStatuses(){
		return statusRepository.findAll();
	}
	public Status getStatus(Integer id) {
		return statusRepository.getReferenceById(id);
	}
	public Status addStatus(Status status) {
		return statusRepository.save(status);
	}
	public void deleteStatus(Integer id) {
		statusRepository.deleteById(id);
	}
	public Status updateStatus(Status status) {
		Status old = statusRepository.getReferenceById(status.getId());
		old.setStatus(status.getStatus());
		return old;
	}
}
