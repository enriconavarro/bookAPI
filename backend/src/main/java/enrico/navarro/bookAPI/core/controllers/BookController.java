package enrico.navarro.bookAPI.core.controllers;

import enrico.navarro.bookAPI.core.models.Book;
import enrico.navarro.bookAPI.core.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/book")
	public Book save(@RequestBody Book book) {

		this.bookService.save(book);

		return book;
	}

	@GetMapping("/book/{id}")
	public Book getById(@PathVariable Long id) {

		return this.bookService.getById(id);
	}

}
