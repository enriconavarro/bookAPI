package enrico.navarro.bookAPI.core.models.dto;

import enrico.navarro.bookAPI.core.models.entity.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BookList {

	private int numberBooks;
	private List<Book> books;

	public BookList() {

		this.books = new ArrayList<>();
	}
}
