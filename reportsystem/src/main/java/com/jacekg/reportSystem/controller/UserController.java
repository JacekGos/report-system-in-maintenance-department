package com.jacekg.reportSystem.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacekg.reportSystem.entity.ProductionLine;
import com.jacekg.reportSystem.entity.ProductionMachine;
import com.jacekg.reportSystem.entity.User;
import com.jacekg.reportSystem.form_entity.FormUser;
import com.jacekg.reportSystem.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	private Map<String, String> roles;
	
	@PostConstruct
	protected void loadRoles() {
		
		roles = new LinkedHashMap<String, String>();
		
		roles.put("ROLE_EMPLOYEE", "Employee");
		roles.put("ROLE_MANAGER", "Manager");
		roles.put("ROLE_ADMIN", "Admin");
		
	}
	
	@GetMapping("/showUsersList")
	public String showUsersList(Model model) {

		List<User> users = userService.getUsers();

		model.addAttribute("users", users);

		return "users-list";
	}
	
	@GetMapping("/showUserDetails")
	public String showUserDetails(@RequestParam("id") long userId, Model model) {
		
		User user = userService.getUser(userId);
		
		model.addAttribute("user", user);		
		
		return "user-details";
	}
	
	@GetMapping("/showUserForm")
	public String showUserForm(Model model) {
		
		model.addAttribute("formUser", new FormUser());
		model.addAttribute("roles", roles);
		
		return "user-form";
	}

}
