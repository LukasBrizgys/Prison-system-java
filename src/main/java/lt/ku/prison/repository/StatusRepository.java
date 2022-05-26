package lt.ku.prison.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ku.prison.entities.Status;

public interface StatusRepository extends JpaRepository<Status, Integer>{
}
