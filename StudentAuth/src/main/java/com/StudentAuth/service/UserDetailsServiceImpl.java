package com.StudentAuth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.StudentAuth.repository.UserRepository;
import com.gl.StudentAuth.entity.User;
import com.gl.StudentAuth.security.MyUserDetails;

public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userrepo.getUserByUsername(username);
		
		if (user ==  null) {
			throw new UsernameNotFoundException("Could Not Find User");
		}
		return new MyUserDetails(user);
	}

}
