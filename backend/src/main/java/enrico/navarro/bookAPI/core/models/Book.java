package enrico.navarro.bookAPI.core.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@Entity
@Table(name = "book", schema = "book_api")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty
	private Long id;

	@NotNull(message = "book.title.req")
	@Size(max = 255, message = "book.title.max")
	@Column
	@JsonProperty
	private String title;

	@NotNull(message = "book.description.req")
	@Column
	@JsonProperty
	private String description;

	@NotNull(message = "book.isbn.req")
	@Column
	@JsonProperty
	private Long isbn;

	@NotNull(message = "book.language.req")
	@Size(min = 2, max = 2, message = "book.language.size")
	@Column
	@JsonProperty
	private String language;
}
