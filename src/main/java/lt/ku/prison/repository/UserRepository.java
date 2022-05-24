package lt.ku.prison.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ku.prison.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmail(String email);
}
