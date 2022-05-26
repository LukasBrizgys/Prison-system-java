package lt.ku.prison.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.ku.prison.entities.Role;
import lt.ku.prison.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	
	public List<Role> getAllRoles(){
		return roleRepository.findAll();
	}
	public Role getRole(Integer id) {
		return roleRepository.getReferenceById(id);
	}
	public Role addRole(Role role) {
		return roleRepository.save(role);
	}
	public void deleteRole(Integer id) {
		roleRepository.deleteById(id);
	}
	public Role updateRole(Role role) {
		Role old = roleRepository.getReferenceById(role.getId());
		old.setRole(role.getRole());
		return old;
	}
	public Role getRoleByName(String role) {
		return roleRepository.findByRole(role);
	}
}
