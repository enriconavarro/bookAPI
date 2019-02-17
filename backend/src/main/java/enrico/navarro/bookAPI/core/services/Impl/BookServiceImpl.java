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

	/***
	 * Função que lê uma página e busca todos os livros de acordo com um formato específico.
	 * @param doc Document da página onde será feita a busca
	 * @return o DTO bookList com suas informações preenchidas
	 * @throws IOException, caso ocorra algum erro para acessar a página
	 */
	private BookList getBooks(Document doc) throws IOException {

		Element conteudo = doc.selectFirst("article");
		BookList bookList = new BookList();

		for (int i = 0; i < conteudo.children().size();) {

			Element element = conteudo.child(i);

			// Caso encontre algum h2, cria um livro
			if (element.tagName().equals("h2")) {

				Book book = new Book();

				book.setTitle(element.ownText());

				element = conteudo.child(++i);

				// Continua procurando pelos outros atributos do livro até encontrar outro livro ou ao final da lista
				while (!element.tagName().equals("h2") && i < conteudo.children().size()) {

					switch (element.tagName()) {

						case "div":
							book.setLanguage(element.ownText().toUpperCase());
							break;

						case "p":

							Element link = element.selectFirst("a");

							if (link != null && link.ownText().equalsIgnoreCase(book.getTitle())) {

								book.setDescription(link.ownText());

								book.setIsbn(this.getIsbn(link.attr("href")));
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

	/***
	 * Função que a partir de uma url procura o ISBN do livro na página.
	 * Foi feito um tratamento especial para a página da packtpub, pois ela informam o ISBN de uma forma
	 * diferente.
	 * @param url, string com a url da página em que o ISBN deve ser buscado
	 * @return o ISBN encontrado, ou caso não seja encontrado, Unavaliable
	 * @throws IOException, em caso de algum problema ao ler a página
	 */
	private String getIsbn(String url) throws IOException {

		Document doc = Jsoup.connect(url).get();

		if (url.contains("packtpub")) {

			return doc.selectFirst("span[itemprop$=isbn]").ownText();

		} else {

			String html = doc.toString();

			Pattern ISBN_PATTERN = Pattern.compile("(ISBN[-]*(1[03])*[ ]*(: ){0,1})(([0-9Xx][- ]*){13}|([0-9Xx][- ]*){10})");
			Matcher m = ISBN_PATTERN.matcher(html);

			if (m.find()) {

				return m.group().replaceAll("(ISBN[-]*(1[03])*[ ]*(: ){0,1})", "").trim();
			}
		}

		return "Unavaliable";
	}
}
