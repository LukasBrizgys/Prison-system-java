package lt.ku.prison.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ku.prison.entities.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Integer>{

}
