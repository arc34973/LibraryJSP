package de.liu.mylibrary.controller;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.liu.mylibrary.model.BorrowHistory;
import de.liu.mylibrary.model.Role;
import de.liu.mylibrary.model.Status;
import de.liu.mylibrary.model.User;
import de.liu.mylibrary.service.LibraryService;

@Controller
public class UserController {

	@Autowired
	LibraryService libraryService;

	// list all users
	@RequestMapping(value = "/list-users", method = RequestMethod.GET)
	public String showUsers(ModelMap model) {
		model.put("users", libraryService.findAllUsers());
		Role userRole = getUserRole();
		model.put("role", userRole);
		model.put("isAdmin", "ROLE_ADMIN".equals(userRole.toString()));
		model.put("isSearchResult", false);
		return "list-users";
	}

	// add users
	@RequestMapping(value = "/add-user", method = RequestMethod.GET)
	public String initCreationForm(ModelMap model) {
		User user = new User();
		model.put("user", user);
		Role userRole = getUserRole();
		model.put("role", userRole);
		model.put("isAdmin", "ROLE_ADMIN".equals(userRole.toString()));

		// dropdown for user status
		Map<String, String> allStatus = new LinkedHashMap<String, String>();
		for (Status status : Status.values()) {
			allStatus.put(status.name(), status.name());
		}
		model.put("allStatus", allStatus);

		// dropdown for user role
		Map<String, String> allRole = new LinkedHashMap<String, String>();
		for (Role role : Role.values()) {
			allRole.put(role.name(), role.name());
		}
		model.put("allRole", allRole);

		return "userForm";
	}

	// deliver Form to add user
	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	public String processCreationForm(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return "userForm";
		} else {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(user.getPassword().trim());
			user.setPassword(encodedPassword);
			this.libraryService.save(user);
		}
		return "redirect:/list-users";
	}

	// update user details per Id
	@RequestMapping(value = "/update_user", method = RequestMethod.GET)
	public String initEditUserForm(@RequestParam("id") int userId, ModelMap model) {
		User user = libraryService.findByUserId(userId);
		model.put("user", user);
		Role userRole = getUserRole();
		model.put("role", userRole);
		model.put("isAdmin", "ROLE_ADMIN".equals(userRole.toString()));

		// dropdown for user status
		Map<String, String> allStatus = new LinkedHashMap<String, String>();
		for (Status status : Status.values()) {
			allStatus.put(status.name(), status.name());
		}
		model.put("allStatus", allStatus);

		// dropdown for user role
		Map<String, String> allRole = new LinkedHashMap<String, String>();
		for (Role role : Role.values()) {
			allRole.put(role.name(), role.name());
		}
		model.put("allRole", allRole);

		return "userForm";
	}

	@RequestMapping(value = "/update_user", method = RequestMethod.POST)
	public String processEditUserForm(ModelMap model, @Valid User user, BindingResult result) {

		if (result.hasErrors()) {
			Role userRole = getUserRole();
			model.put("role", userRole);
			model.put("isAdmin", "ROLE_ADMIN".equals(userRole.toString()));
			return "userForm";
		}

		// to get the borrowHistory
		User u = libraryService.findByUserId(user.getId());

		user.setBorrowHistories(u.getBorrowHistories());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword().trim());
		user.setPassword(encodedPassword);
		libraryService.save(user);
		return "redirect:/list-users";
	}

	// delete User

	@RequestMapping(value = "/delete_user", method = RequestMethod.GET)
	public String processDeleteUserForm(@RequestParam("id") int userId, ModelMap model) {
		User u = libraryService.findByUserId(userId);
		libraryService.delete(u);
		return "redirect:/list-users";
	}

	// get the searchForm
	@RequestMapping(value = "/search-user", method = RequestMethod.GET)
	public String initFindForm(ModelMap model) {
		model.put("user", new User());
		Role userRole = getUserRole();
		model.put("role", userRole);
		model.put("isAdmin", "ROLE_ADMIN".equals(userRole.toString()));
		return "/search-user";
	}

	// show lastNameSearchResult
	@RequestMapping(value = "/search-user", method = RequestMethod.POST)
	public String processFindForm(User user, BindingResult result, ModelMap model) {
		if (user.getLastName() == null) {
			user.setLastName("");
		}

		Collection<User> users = this.libraryService.findByLastName(user.getLastName());
		if (users.isEmpty()) {
			result.rejectValue("lastName", "not found", "not found");
			return "/search-user";
		}

		model.put("users", users);
		model.put("user", new User());
		model.put("isSearchResult", true);
		Role userRole = getUserRole();
		model.put("role", userRole);
		model.put("isAdmin", "ROLE_ADMIN".equals(userRole.toString()));
		return "/list-users";
	}

	// show userdetails after search

	@RequestMapping(value = "/showDetails", method = RequestMethod.GET)
	public String showUserDetails(@RequestParam("id") int userId, ModelMap model) {
		User user = libraryService.findByUserId(userId);
		model.put("user", user);
		Role userRole = getUserRole();
		model.put("role", userRole);
		model.put("isAdmin", "ROLE_ADMIN".equals(userRole.toString()));

		return "userDetails";
	}

	// show user borrow history after search
	@RequestMapping(value = "/show_History", method = RequestMethod.GET)
	public String showUserHistory(@RequestParam("id") int userId, ModelMap model) {
		Collection<BorrowHistory> borrowHistories = libraryService.findBorrowHistoryByUserId(userId);
		model.put("borrowHistories", borrowHistories);
		Role userRole = getUserRole();
		model.put("role", userRole);
		model.put("isAdmin", "ROLE_ADMIN".equals(userRole.toString()));
		model.put("showBorrowList", true);
		return "showHistory";
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
