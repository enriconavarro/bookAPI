package enrico.navarro.bookAPI.core.services.Impl;

import enrico.navarro.bookAPI.core.models.dto.BookList;
import enrico.navarro.bookAPI.core.models.entity.Book;
import enrico.navarro.bookAPI.core.repositories.BookRepository;
import enrico.navarro.bookAPI.core.services.BookService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	@Override
	public BookList findAll() {

		try {

			Document doc = Jsoup.connect("https://kotlinlang.org/docs/books.html").get();

			return this.getBooks(doc);

		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}
	}

	private BookList getBooks(Document doc) throws IOException {

		Element conteudo = doc.selectFirst("article");
		BookList bookList = new BookList();

		for (int i = 0; i < conteudo.children().size();) {

			Element element = conteudo.child(i);

			if (element.tagName().equals("h2")) {

				Book book = new Book();

				book.setTitle(element.ownText());

				element = conteudo.child(++i);

				while (!element.tagName().equals("h2") && i < conteudo.children().size()) {

					switch (element.tagName()) {

						case "div":
							book.setLanguage(element.ownText());
							break;

						case "p":

							Element link = element.selectFirst("a");

							if (link != null && link.ownText().equalsIgnoreCase(book.getTitle())) {

								book.setDescription(link.ownText());

								book.setIsbn(this.getIsbn(link));
							}

							if (book.getDescription() == null) {

								book.setDescription(element.ownText());

							} else {

								book.setDescription(book.getDescription() + " " + element.ownText());
							}
							break;
					}

					if (i + 1 < conteudo.children().size()) {

						element = conteudo.child(++i);

					} else {

						i++;
					}
				}

				bookList.getBooks().add(book);
			}
		}

		bookList.setNumberBooks(bookList.getBooks().size());
		return bookList;
	}

	private String getIsbn(Element link) throws IOException {

		Document doc = Jsoup.connect(link.attr("href")).get();

		String html = doc.toString();

		Pattern ISBN_PATTERN = Pattern.compile("(ISBN[-]*(1[03])*[ ]*(: ){0,1})(([0-9Xx][- ]*){13}|([0-9Xx][- ]*){10})");
		Matcher m = ISBN_PATTERN.matcher(html);

		if (m.find()) {

			return m.group();
		}

		return "Unavaliable";
	}
}
