package com.jacekg.reportSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jacekg.reportSystem.entity.User;
import com.jacekg.reportSystem.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/showUsersList")
	public String showUsersList(Model model) {

		List<User> users = userService.getUsers();

		model.addAttribute("users", users);

		return "users-list";
	}

}
