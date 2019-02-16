package enrico.navarro.bookAPI.core.services;

import enrico.navarro.bookAPI.core.models.dto.BookList;
import enrico.navarro.bookAPI.core.models.entity.Book;

public interface BookService {

	void save(Book book);

	Book getById(Long id);

	BookList findAll();
}
