package lt.ku.prison.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ku.prison.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
}
