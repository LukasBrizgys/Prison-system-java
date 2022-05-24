package lt.ku.prison.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.ku.prison.entities.City;
import lt.ku.prison.repository.CityRepository;

@Service
public class CityService {
	@Autowired
	CityRepository cityRepository;
	
	public List<City> getCities(){
		return cityRepository.findAll();
	}
	
	public City getCity(Integer id) {
		return cityRepository.getReferenceById(id);
	}
	public City addCity(City city) {
		return cityRepository.save(city);
	}
	public void deleteCity(Integer id) {
		cityRepository.deleteById(id);
	}
	public City updateCity(City city) {
		City old = cityRepository.getReferenceById(city.getId());
		old.setName(city.getName());
		return old;
	}
}
