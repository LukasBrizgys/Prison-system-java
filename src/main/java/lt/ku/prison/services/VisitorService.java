package lt.ku.prison.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.ku.prison.entities.Visitor;
import lt.ku.prison.repository.VisitorRepository;

@Service
public class VisitorService {
	@Autowired
	VisitorRepository visitorRepository;
	public List<Visitor> getAllVisitors(){
		return visitorRepository.findAll();
	}
	
	public Visitor getVisitor(Integer id) {
		return visitorRepository.getReferenceById(id);
	}
	
	public Visitor addVisitor(Visitor visitor) {
		return visitorRepository.save(visitor);
	}
	
	public Visitor updateVisitor(Visitor visitor) {
		Visitor old = visitorRepository.getReferenceById(visitor.getId());
		old.setName(visitor.getName());
		old.setSurname(visitor.getSurname());
		old.setBirthDate(visitor.getBirthDate());
		visitorRepository.save(old);
		return old;
	}
	
	public void deleteVisitor(Integer id) {
		visitorRepository.deleteById(id);
	}
}
