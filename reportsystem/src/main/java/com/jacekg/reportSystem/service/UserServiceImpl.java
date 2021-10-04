package com.jacekg.reportSystem.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jacekg.reportSystem.dao.RoleDao;
import com.jacekg.reportSystem.dao.UserDao;
import com.jacekg.reportSystem.entity.Role;
import com.jacekg.reportSystem.entity.User;
import com.jacekg.reportSystem.form_entity.FormUser;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	@Transactional("appTransactionManager")
	public User findByUserName(String userName) {
		
		return userDao.findByUserName(userName);
	}
	
	@Override
	@Transactional
	public void save(FormUser formUser) {
		
		User user = new User();
		
		user.setUserName(formUser.getUserName());
		user.setPassword(passwordEncoder.encode(formUser.getPassword()));
		user.setFirstName(StringUtils.capitalize(formUser.getFirstName()));
		user.setLastName(StringUtils.capitalize(formUser.getLastName()));
		user.setEmail(formUser.getEmail());
		user.setEnabled(true);
		user.setNonExpired(true);
		user.setCredentialsNonExpired(true);
		user.setNonLocked(true);
		
		if (formUser.getRole().equals("ROLE_EMPLOYEE")) {
			user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));
		} 
		else if (formUser.getRole().equals("ROLE_MANAGER")) {
			user.setRoles(Arrays.asList(
					roleDao.findRoleByName("ROLE_EMPLOYEE"),
					roleDao.findRoleByName("ROLE_MANAGER")));
		} 
		else if (formUser.getRole().equals("ROLE_ADMIN")) {
			user.setRoles(Arrays.asList(
					roleDao.findRoleByName("ROLE_EMPLOYEE"),
					roleDao.findRoleByName("ROLE_MANAGER"),
					roleDao.findRoleByName("ROLE_ADMIN")));
		}
		
		userDao.save(user);
	}

	@Override
	@Transactional("appTransactionManager")
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		User user = userDao.findByUserName(userName);
		if (user == null) {
			
			throw new UsernameNotFoundException("Invalid username or password");
		}
		
		return new org.springframework.security.core.userdetails.User(
				user.getUserName(), 
				user.getPassword(),
				user.isEnabled(),
				user.isNonExpired(),
				user.isCredentialsNonExpired(),
				user.isNonLocked(),
				mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<User> getUsers() {
		return userDao.getUsers();
	}

	@Override
	@Transactional
	public User getUser(long userId) {
		return userDao.getUser(userId);
	}

	@Override
	@Transactional
	public Long getUsersAmount(String firstName, String lastName) {
		return userDao.getUsersAmount(firstName, lastName);
	}

}




