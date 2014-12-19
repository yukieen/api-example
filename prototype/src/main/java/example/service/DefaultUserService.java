package example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.datasource.UserMapper;
import example.model.User;

@Service	
public class DefaultUserService implements UserService {

	@Autowired
	private UserMapper mapper;
	
	@Override
	public void add(User user) {
		mapper.add(user);
	}

	@Override
	public List<User> findAll() {
		return mapper.findAll();
	}

	@Override
	public User find(Long id) {
		return mapper.find(id);
	}

}
