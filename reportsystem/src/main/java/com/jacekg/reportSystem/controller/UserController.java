package com.jacekg.reportSystem.controller;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import com.jacekg.reportSystem.dto.FormChangePassword;
import com.jacekg.reportSystem.dto.FormUser;
import com.jacekg.reportSystem.entity.User;
import com.jacekg.reportSystem.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	private Map<String, String> roles;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostConstruct
	protected void loadRoles() {

		roles = new LinkedHashMap<String, String>();

		roles.put("EMPLOYEE", "Employee");
		roles.put("MANAGER", "Manager");
		roles.put("ADMIN", "Admin");
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

		FormUser formUser = fillFormUser(user);

		model.addAttribute("user", user);
		model.addAttribute("roles", roles);
		model.addAttribute("formUser", formUser);

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
		formUser.setId(0L);

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

	@GetMapping("/deactivateUserAccount")
	public String deactivateUserAccount(@RequestParam("id") long userId, Model model) {

		userService.deactivateUser(userId);

		model.addAttribute("id", userId);	

		return "redirect:/user/showUserDetails";
	}

	@GetMapping("/activateUserAccount")
	public String activateUserAccount(@RequestParam("id") long userId, Model model) {

		userService.activateUser(userId);

		model.addAttribute("id", userId);	

		return "redirect:/user/showUserDetails";
	}

	@PostMapping("/setUserRole")
	public String setUserRole(@ModelAttribute("formUser") FormUser formUser, Model model) {

		userService.save(formUser);

		model.addAttribute("id", formUser.getId());	

		return "redirect:/user/showUserDetails";
	}
	
	@GetMapping("/showUserOptions")
	public String showUserOptions(Principal principal, Model model) {
		
		String userName = principal.getName();
		
		Long userId = userService.getUserId(userName);
		
		model.addAttribute("userId", userId);
		
		System.out.println(userName);
		
		return "user-options";
	}
	
	@GetMapping("/changeUserPassword")
	public String changeUserPassword(@RequestParam("userId") Long userId, Model model) {
		
		FormChangePassword formChangePassword = new FormChangePassword();
		formChangePassword.setId(userId);
		
		model.addAttribute("formChangePassword", formChangePassword);
		
		return "change-password";
	}
	
	@PostMapping("/processChangePassword")
	public String processChangePassword(
			@ModelAttribute("formChangePassword") FormChangePassword formChangePassword, Model model) {

		System.out.println("My logs: " + formChangePassword.getId());
		System.out.println("My logs: " + formChangePassword.getPassword());
		System.out.println("My logs: " + formChangePassword.getMatchingPassword());
		
		User user = userService.getUser(formChangePassword.getId());
		
		if (user != null) {
			
			FormUser formUser = new FormUser();
			formUser = fillFormUser(user);
			System.out.println("My logs: " + formUser.getRole());
			formUser.setPassword(formChangePassword.getPassword());
			
			userService.save(formUser);
		} else if (user == null) {
			model.addAttribute("changePasswordError", "Zmiana hasła nie powiodła się.");
		}
		
		return "redirect:/user/showUserOptions";
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

	private FormUser fillFormUser(User user) {

		FormUser formUser = new FormUser();
		formUser.setId(user.getId());
		formUser.setFirstName(user.getFirstName());
		formUser.setLastName(user.getLastName());
		formUser.setUserName(user.getUserName());
		formUser.setPassword(user.getPassword());
		formUser.setEmail(user.getEmail());
		formUser.setRole(user.getRoleName());
		formUser.setEnabled(user.isEnabled());
		formUser.setCredentialsNonExpired(user.isCredentialsNonExpired());
		formUser.setNonExpired(user.isNonExpired());
		formUser.setNonLocked(user.isNonLocked());

		return formUser;
	}
}
