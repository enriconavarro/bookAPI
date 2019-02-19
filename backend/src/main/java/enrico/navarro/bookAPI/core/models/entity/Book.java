package enrico.navarro.bookAPI.core.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

	@NotEmpty(message = "{book.title.req}")
	@Size(max = 255, message = "{book.title.size}")
	@Column
	@JsonProperty
	private String title;

	@NotEmpty(message = "{book.description.req}")
	@Column
	@JsonProperty
	private String description;

	@NotEmpty(message = "{book.isbn.req}")
	@Column
	@JsonProperty
	private String isbn;

	@NotEmpty(message = "{book.language.req}")
	@Size(min = 2, max = 2, message = "{book.language.size}")
	@Column
	@JsonProperty
	private String language;
}
