package lt.ku.prison.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ku.prison.entities.City;

public interface CityRepository extends JpaRepository<City, Integer>{
	

}
