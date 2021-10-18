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
import org.w3c.dom.Text;

import com.jacekg.reportSystem.dto.ChangePasswordDto;
import com.jacekg.reportSystem.dto.UserDto;
import com.jacekg.reportSystem.entity.User;
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
	public String showUserDetails(@RequestParam("id") long userId, Model model, Principal principal) {
		
		User user = userService.getUserWithRoles(userId);

		UserDto userDto = fillUserDto(user);
		
		String currentUserName = principal.getName();

		model.addAttribute("user", user);
		model.addAttribute("currentUserName", currentUserName);
		model.addAttribute("roles", roles);
		model.addAttribute("userDto", userDto);

		return "user-details";
	}

	@GetMapping("/showUserForm")
	public String showUserForm(Model model) {

		model.addAttribute("userDto", new UserDto());
		model.addAttribute("roles", roles);

		return "user-form";
	}

	@PostMapping("/processUserForm")
	public String processUserForm(@Valid @ModelAttribute("userDto") UserDto userDto ,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			
			model.addAttribute("roles", roles);
			
			return "user-form";
		}
		
		String firstName = userDto.getFirstName();
		String lastName = userDto.getLastName();
		
		firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase().replaceAll("\\s+", "");
		lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase().trim().replaceAll("\\s+", "");
		
		userDto.setFirstName(firstName);
		userDto.setLastName(lastName);
		
		userDto.setUserName(generateUserName(userDto.getFirstName(), userDto.getLastName()));
		userDto.setPassword("password");
		userDto.setId(0L);

		return "user-confirmation";
	}

	@PostMapping("/processSaveUser")
	public String processSaveUser(@ModelAttribute("userDto") UserDto userDto, Model model) {

		System.out.println("My logs formUser: " + userDto.toString());

		if (userService.findByUserName(userDto.getUserName()) != null) {

			model.addAttribute("registrationError", "Wystąpił błąd - nazwa konta zajęta.");

			return "user-confirmation";
		}

		userService.save(userDto);

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
	public String setUserRole(@ModelAttribute("userDto") UserDto userDto, Model model) {

		userService.save(userDto);

		model.addAttribute("id", userDto.getId());	

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
		
		ChangePasswordDto changePasswordDto = new ChangePasswordDto();
		changePasswordDto.setId(userId);
		
		model.addAttribute("changePasswordDto", changePasswordDto);
		
		return "change-password";
	}
	
	@PostMapping("/processChangePassword")
	public String processChangePassword(
			@Valid @ModelAttribute("changePasswordDto") ChangePasswordDto changePasswordDto, 
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "change-password";
		}
		System.out.println("My logs password: " + changePasswordDto.getPassword());
//		changePasswordDto.setPassword(changePasswordDto.getPassword().replaceAll("\\s+", ""));
		
		User user = userService.getUserWithRoles(changePasswordDto.getId());
		
		if (user != null) {
			
			UserDto userDto = new UserDto();
			userDto = fillUserDto(user);
			userDto.setPassword(changePasswordDto.getPassword());
			
			userService.save(userDto);
			
			model.addAttribute("changePasswordSuccess", "Zmiana hasła powiodła się.");
			
		} else if (user == null) {
			model.addAttribute("changePasswordError", "Zmiana hasła nie powiodła się.");
		}
		
		model.addAttribute("changePasswordDto", new ChangePasswordDto());
		
		return "change-password";
	}
	
	private String generateUserName(String firstName, String lastName) {
		System.out.println("My logs username: " + firstName + " " + lastName);
		
		
		
		System.out.println("My logs username: " + firstName + " " + lastName);
		
		char firstPart = firstName.toLowerCase().charAt(0);

		String userName = firstPart + lastName.toLowerCase();

		Long userNumber = userService.getUsersAmount(firstName, lastName);

		if (userNumber == 0) {
			return userName;
		}

		return userName + ++userNumber;
	}

	private UserDto fillUserDto(User user) {

		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setUserName(user.getUserName());
		userDto.setPassword(user.getPassword());
		userDto.setEmail(user.getEmail());
		userDto.setRole(user.getRoleName());
		userDto.setEnabled(user.isEnabled());
		userDto.setCredentialsNonExpired(user.isCredentialsNonExpired());
		userDto.setNonExpired(user.isNonExpired());
		userDto.setNonLocked(user.isNonLocked());

		return userDto;
	}
}
