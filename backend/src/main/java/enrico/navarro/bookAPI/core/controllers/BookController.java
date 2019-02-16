package enrico.navarro.bookAPI.core.controllers;

import enrico.navarro.bookAPI.core.models.Book;
import enrico.navarro.bookAPI.core.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/book")
	public Book getImageEnvolvidoBase64(@RequestBody Book book) {

		this.bookService.save(book);

		return book;
	}

}
