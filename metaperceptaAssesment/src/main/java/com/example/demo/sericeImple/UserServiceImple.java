package com.example.demo.sericeImple;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;

import jakarta.persistence.NoResultException;

@Service
public class UserServiceImple implements UserService {

	@Autowired
	private UserRepo repo;

	@Override
	public User registerUser(User user) {

		if (user == null) {
			throw new NoResultException("user is empty");
		} else {

			return repo.save(user);
		}

	}

	@Override
	public User updateUser(User user) {

		if (user != null) {
			Integer id = user.getId();
			if (id != null) {
				User id2 = repo.findById(id).get();

				id2.setName(user.getName());
				id2.setEmail(user.getEmail());
				id2.setPhoneNumber(user.getPhoneNumber());
				repo.save(id2);
				return id2;
			}else {
				throw new NoResultException("id Not found");
			}
		}else {
			throw new NoResultException("data is empty found");
		}

		
	}

}
