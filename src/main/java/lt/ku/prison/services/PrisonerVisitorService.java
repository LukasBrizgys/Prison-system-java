package lt.ku.prison.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.ku.prison.entities.PrisonerVisitor;
import lt.ku.prison.repository.PrisonerVisitorRepository;

@Service
public class PrisonerVisitorService {
	@Autowired
	PrisonerVisitorRepository prisonerVisitorRepository;
	
	public PrisonerVisitor addPrisonerVisitor(PrisonerVisitor prisonerVisitor) {
		return prisonerVisitorRepository.save(prisonerVisitor);
	}
	public void deletePrisonerVisitor(Integer prisonerId, Integer visitorId, String startDate) {
		prisonerVisitorRepository.deletePrisonerVisitor(prisonerId, visitorId, startDate);
	}
	public List<PrisonerVisitor> getVisitorsByPrisonerId(Integer prisonerId) {
		return prisonerVisitorRepository.findPrisonerVisitorsByPrisonerId(prisonerId);
	}
}
