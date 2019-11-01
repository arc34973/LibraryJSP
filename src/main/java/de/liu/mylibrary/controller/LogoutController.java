package de.liu.mylibrary.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class LogoutController {

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}

		return "redirect:/";
	}

	@RequestMapping(value = "/session/invalid", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public String invalid() {

		return "Time out, please log in again!";
	}
}
