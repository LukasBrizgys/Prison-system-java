package lt.ku.prison.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lt.ku.prison.entities.User;
import lt.ku.prison.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	public User getUser(Integer id) {
		return userRepository.getReferenceById(id);
	}
	public User addUser(User user) {
		user.setPassword((new BCryptPasswordEncoder()).encode(user.getPassword()));
		return userRepository.save(user);
	}
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("Vartotojas nerastas");
		}
		return user;
	}
	
}
