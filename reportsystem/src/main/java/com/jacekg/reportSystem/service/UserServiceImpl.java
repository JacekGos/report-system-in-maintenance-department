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
import com.jacekg.reportSystem.dto.UserDto;
import com.jacekg.reportSystem.entity.Role;
import com.jacekg.reportSystem.entity.User;

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
	public void save(UserDto formUser) {
		
		User user = new User();
		
		user = new User(
				formUser.getId(),
				formUser.getUserName(),
				passwordEncoder.encode(formUser.getPassword()),
				StringUtils.capitalize(formUser.getFirstName()),
				StringUtils.capitalize(formUser.getLastName()),
				formUser.getEmail(),
				formUser.isEnabled(),
				true,
				true,
				true);
		
		if (formUser.getRole().equals("EMPLOYEE")) {
			user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));
		} 
		else if (formUser.getRole().equals("MANAGER")) {
			user.setRoles(Arrays.asList(
					roleDao.findRoleByName("ROLE_EMPLOYEE"),
					roleDao.findRoleByName("ROLE_MANAGER")));
		} 
		else if (formUser.getRole().equals("ADMIN")) {
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
	public User getUserWithRoles(long userId) {
		return userDao.getUserWithRoles(userId);
	}

	@Override
	@Transactional
	public Long getUsersAmount(String firstName, String lastName) {
		return userDao.getUsersAmount(firstName, lastName);
	}

	@Override
	@Transactional
	public void setUserPassword(long userId, String password) {
		
		User user = userDao.getUserWithRoles(userId);
//		user.getRoles();
		user.setPassword(passwordEncoder.encode(password));
		
		userDao.save(user);
	}

	@Override
	@Transactional
	public void deactivateUser(long userId) {
		
		User user = userDao.getUserWithRoles(userId);
		user.setEnabled(false);
		
		userDao.save(user);
	}

	@Override
	@Transactional
	public void activateUser(long userId) {
		
		User user = userDao.getUserWithRoles(userId);
		user.setEnabled(true);
		
		userDao.save(user);
	}

	@Override
	@Transactional
	public Long getUserId(String userName) {
		return userDao.getUserId(userName);
	}

}




