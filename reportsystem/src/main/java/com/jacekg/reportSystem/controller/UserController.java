package com.jacekg.reportSystem.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);			
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
	
	@PostMapping("/processUserForm")
	public String processUserForm(@Valid @ModelAttribute("formUser") FormUser formUser,
				BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "user-form";
		}
		
		formUser.setFirstName(StringUtils.capitalize(formUser.getFirstName()));
		formUser.setLastName(StringUtils.capitalize(formUser.getLastName()));
		formUser.setUserName(generateUserName(formUser.getFirstName(), formUser.getLastName()));
		formUser.setPassword("password");
		
		return "user-confirmation";
	}
	
	@PostMapping("/processSaveUser")
	public String processSaveUser(@ModelAttribute("formUser") FormUser formUser, Model model) {
		
		System.out.println("My logs formUser: " + formUser.toString());
		
		if (userService.findByUserName(formUser.getUserName()) != null) {
			
			model.addAttribute("registrationError", "Wystąpił błąd - nazwa konta zajęta.");
			
			return "user-confirmation";
		}
		
		userService.save(formUser);
		
		return "redirect:/user/showUsersList";
	}
	
	@GetMapping("/resetUserPassword")
	public String resetPassword(@RequestParam("id") long userId, Model model) {
		
		userService.setUserPassword(userId, "password");
		
		model.addAttribute("id", userId);	
		
		return "redirect:/user/showUserDetails";
	}
	
	@PostMapping("/blockUserAccount")
	public String blockUserAccount() {
		
		return "user-details";
	}
	
	private String generateUserName(String firstName, String lastName) {
		
		char firstPart = firstName.toLowerCase().charAt(0);
		
		String userName = firstPart + lastName.toLowerCase();
		
		Long userNumber = userService.getUsersAmount(firstName, lastName);
		
		if (userNumber == 0) {
			return userName;
		}
		
		return userName + ++userNumber;
	}
}
