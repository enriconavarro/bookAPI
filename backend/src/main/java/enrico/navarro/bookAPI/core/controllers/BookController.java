package enrico.navarro.bookAPI.core.controllers;

import enrico.navarro.bookAPI.core.models.dto.BookList;
import enrico.navarro.bookAPI.core.models.entity.Book;
import enrico.navarro.bookAPI.core.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/book")
	public Book save(@Valid @RequestBody Book book) {

		this.bookService.save(book);

		return book;
	}

	@GetMapping("/book/{id}")
	public Book getById(@PathVariable Long id) {

		return this.bookService.getById(id);
	}

	@GetMapping("/books")
	public BookList findAll() {

		return this.bookService.findAll();
	}

}
