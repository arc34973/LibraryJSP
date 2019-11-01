package de.liu.mylibrary.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.liu.mylibrary.model.Book;
import de.liu.mylibrary.model.BookType;
import de.liu.mylibrary.model.BorrowHistory;
import de.liu.mylibrary.model.Role;
import de.liu.mylibrary.model.Status;
import de.liu.mylibrary.model.User;
import de.liu.mylibrary.service.LibraryService;

@Controller
public class BookController {

	@Autowired
	LibraryService libraryService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		binder.registerCustomEditor(LocalDate.class, new CustomLocalDateEditor(formatter));
	}

	// list of all books
	@RequestMapping(value = "/list-books", method = RequestMethod.GET)
	public String showbooks(ModelMap model) {
		model.put("books", libraryService.findAllBooks());
		Role userRole = getUserRole();
		model.put("role", userRole);
		model.put("isAdmin", "ROLE_ADMIN".equals(userRole.toString()));
		model.put("searchModus", false);
		return "list-books";
	}

	// add book ("get")
	@RequestMapping(value = "/add-book", method = RequestMethod.GET)
	public String initCreationForm(ModelMap model) {
		Book book = new Book();
		model.put("book", book);

		// dropdown for book type
		Map<String, String> allType = new LinkedHashMap<String, String>();
		for (BookType bookType : BookType.values()) {
			allType.put(bookType.name(), bookType.name());
		}

		// dropdown for book status
		Map<String, String> allStatus = new LinkedHashMap<String, String>();
		for (Status status : Status.values()) {
			allStatus.put(status.name(), status.name());
		}
		model.put("allStatus", allStatus);

		model.put("allType", allType);
		Role userRole = getUserRole();
		model.put("role", userRole);
		model.put("isAdmin", "ROLE_ADMIN".equals(userRole.toString()));
		return "bookForm";
	}

	// add book ("post")
	@RequestMapping(value = "/add-book", method = RequestMethod.POST)
	public String processCreationForm(@Valid Book book, BindingResult result) {
		if (result.hasErrors()) {
			return "bookForm";
		} else {
			this.libraryService.save(book);
		}
		return "redirect:/list-books";
	}

	// delete Book

	@RequestMapping(value = "/delete_book", method = RequestMethod.GET)
	public String processDeleteBookForm(@RequestParam("id") int bookId, ModelMap model) {
		Book book = libraryService.findByBookId(bookId);
		libraryService.delete(book);
		return "redirect:/list-books";
	}

	// get the searchForm
	@RequestMapping(value = "/list-search", method = RequestMethod.GET)
	public String initFindForm(ModelMap model) {
		model.put("book", new Book());
		Role userRole = getUserRole();
		model.put("role", userRole);
		model.put("isAdmin", "ROLE_ADMIN".equals(userRole.toString()));
		return "list-search";
	}

	// show titleSearchResult
	@RequestMapping(value = "/list-search", method = RequestMethod.POST)
	public String processFindForm(Book book, BindingResult result, ModelMap model) {
		if (book.getTitle() == null) {
			book.setTitle("");
		}

		Collection<Book> books = this.libraryService.findByTitle(book.getTitle());
		if (books.isEmpty()) {
			result.rejectValue("title", "not found", "not found");
			return "/list-search";
		}

		model.put("books", books);
		Role userRole = getUserRole();
		model.put("role", userRole);
		model.put("isAdmin", "ROLE_ADMIN".equals(userRole.toString()));
		model.put("searchModus", true);
		return "/list-books";
	}

	// show borrowHistory by bookId
	@RequestMapping(value = "/show_BookHistory", method = RequestMethod.GET)
	public String showBookHistory(@RequestParam("id") int bookId, ModelMap model) {
		Collection<BorrowHistory> borrowHistories = libraryService.findBorrowHistoryByBookId(bookId);
		model.put("borrowHistories", borrowHistories);
		model.put("showBorrowList", true);
		Role userRole = getUserRole();
		model.put("role", userRole);
		model.put("isAdmin", "ROLE_ADMIN".equals(userRole.toString()));

		return "showHistory";
	}

	// show book details after update click
	@RequestMapping(value = "/update_book", method = RequestMethod.GET)
	public String initEditBookForm(@RequestParam("id") int bookId, ModelMap model) {
		Book book = libraryService.findByBookId(bookId);
		model.put("book", book);
		Role userRole = getUserRole();
		model.put("role", userRole);
		model.put("isAdmin", "ROLE_ADMIN".equals(userRole.toString()));

		// dropdown for book type
		Map<String, String> allType = new LinkedHashMap<String, String>();
		for (BookType bookType : BookType.values()) {
			allType.put(bookType.name(), bookType.name());
		}
		model.put("allType", allType);

		// dropdown for book status
		Map<String, String> allStatus = new LinkedHashMap<String, String>();
		for (Status status : Status.values()) {
			allStatus.put(status.name(), status.name());
		}
		model.put("allStatus", allStatus);

		return "bookForm";
	}

	@RequestMapping(value = "/update_book", method = RequestMethod.POST)
	public String processEditBookForm(ModelMap model, @Valid Book book, BindingResult result) {

		if (result.hasErrors()) {
			return "bookForm";
		}

		// to get the borrowHistory
		Book b = libraryService.findByBookId(book.getId());

		book.setBorrowHistories(b.getBorrowHistories());
		libraryService.save(book);
		return "redirect:/list-books";
	}

	// add BorrowHistory
	@RequestMapping(value = "/borrow", method = RequestMethod.GET)
	public String borrowBook(@RequestParam("id") int bookId, ModelMap model) {
		Book book = libraryService.findByBookId(bookId);
		BorrowHistory borrowHistory = new BorrowHistory();
		borrowHistory.setBook(book);
		String userName = getLoggedInUserName();
		borrowHistory.setUser(libraryService.findByUserName(userName));
		LocalDate today = LocalDate.now();
		borrowHistory.setDateBorrow(today);
		borrowHistory.setDateTurnBack(today.plusDays(21));
		book.getBorrowHistories().add(borrowHistory);
		libraryService.save(borrowHistory);
		model.put("showBorrowList", false);
		model.put("borrowHistory", borrowHistory);
		Role userRole = getUserRole();
		model.put("role", userRole);
		model.put("isAdmin", "ROLE_ADMIN".equals(userRole.toString()));

		return "showHistory";
	}

	// update BorrowHistory
	@RequestMapping(value = "/borrowUpdate", method = RequestMethod.GET)
	public String updateBorrowHistory(@RequestParam("id") int bhId, ModelMap model) {
		model.put("borrowHistory", libraryService.findBorrowHistoryById(bhId));
		Role userRole = getUserRole();
		model.put("role", userRole);
		model.put("isAdmin", "ROLE_ADMIN".equals(userRole.toString()));
		return "borrowHistoryForm";
	}

	// Log out
	private String getLoggedInUserName() {
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
