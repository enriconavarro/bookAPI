--
-- Criação da entidade book
--

CREATE TABLE book_api.book(
 id BIGSERIAL PRIMARY KEY,
 title VARCHAR(255) NOT NULL,
 description TEXT NOT NULL,
 isbn BIGINT NOT NULL,
 language CHAR(2) NOT NULL
);

GRANT INSERT,SELECT,UPDATE,DELETE ON TABLE book_api.book TO book_api;
ALTER TABLE book_api.book OWNER TO postgres;

COMMENT ON TABLE book_api.book IS 'Entidade responsável por armazenar os livros da API.';
COMMENT ON COLUMN book_api.book.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN book_api.book.title IS 'Título do livro.';
COMMENT ON COLUMN book_api.book.description IS 'Descrição do livro.';
COMMENT ON COLUMN book_api.book.isbn IS 'International Standard Book Number do livro.';
COMMENT ON COLUMN book_api.book.language IS 'Sigla da linguagem transcrita no livro.';