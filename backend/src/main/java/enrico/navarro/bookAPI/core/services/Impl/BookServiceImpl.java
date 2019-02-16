package enrico.navarro.bookAPI.core.services.Impl;

import enrico.navarro.bookAPI.core.models.Book;
import enrico.navarro.bookAPI.core.repositories.BookRepository;
import enrico.navarro.bookAPI.core.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public void save(Book book) {

		this.bookRepository.save(book);
	}

	@Override
	public Book getById(Long id) {

		return this.bookRepository.getOne(id);
	}
}
