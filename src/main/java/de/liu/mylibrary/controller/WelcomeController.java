package de.liu.mylibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.liu.mylibrary.model.Role;
import de.liu.mylibrary.model.User;
import de.liu.mylibrary.service.LibraryService;

@Controller
public class WelcomeController {

	@Autowired
	LibraryService libraryService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {
		String name = getLoggedInUserName();
		model.put("name", name);
		Role userRole = getUserRole();
		model.put("role", userRole);
		model.put("isAdmin", "ROLE_ADMIN".equals(userRole.toString()));
		return "welcome";
	}

	public String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}

	public Role getUserRole() {
		User user = libraryService.findByUserName(getLoggedInUserName());
		Role role = user.getRole();
		return role;
	}

}
