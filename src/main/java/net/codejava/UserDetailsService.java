package net.codejava;

import java.util.List;
import java.util.Optional;

public interface UserDetailsService {
	List<User> getAllUser();
	
	void saveUser(User user);
	
	void deleteUser(Long id);
	
	Optional<User> findUserById(Long id);

}
