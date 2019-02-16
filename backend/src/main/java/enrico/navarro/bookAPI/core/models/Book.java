package enrico.navarro.bookAPI.core.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@Entity
@Table(name = "book", schema = "book_api")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "book.title.req")
	@Size(max = 255, message = "book.title.max")
	@Column
	private String title;

	@NotNull(message = "book.description.req")
	@Column
	private String description;

	@NotNull(message = "book.isbn.req")
	@Column
	private Long isbn;

	@NotNull(message = "book.language.req")
	@Size(min = 2, max = 2, message = "book.language.size")
	@Column
	private String language;
}
