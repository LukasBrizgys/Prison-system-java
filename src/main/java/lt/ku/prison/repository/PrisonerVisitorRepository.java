package lt.ku.prison.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import lt.ku.prison.classes.PrisonerVisitorKey;
import lt.ku.prison.entities.PrisonerVisitor;

public interface PrisonerVisitorRepository extends JpaRepository<PrisonerVisitor, PrisonerVisitorKey>{

	@Transactional
	@Modifying
	@Query("DELETE FROM PrisonerVisitor PV WHERE PV.id.prisonerId =:prisonerId AND PV.id.visitorId =:visitorId AND PV.id.startDate=:startDate")
	void deletePrisonerVisitor(Integer prisonerId, Integer visitorId, String startDate);
	
	List<PrisonerVisitor> findPrisonerVisitorsByPrisonerId(Integer id);
}
