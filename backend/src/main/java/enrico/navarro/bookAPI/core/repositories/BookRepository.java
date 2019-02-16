package enrico.navarro.bookAPI.core.repositories;

import enrico.navarro.bookAPI.core.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
