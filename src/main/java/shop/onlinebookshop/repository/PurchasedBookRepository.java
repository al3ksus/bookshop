package shop.onlinebookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.onlinebookshop.models.books.Book;
import shop.onlinebookshop.models.books.PurchasedBook;
import shop.onlinebookshop.models.users.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchasedBookRepository extends JpaRepository<PurchasedBook, Long> {
    Optional<List<PurchasedBook>> findAllByBook(Book book);
    Optional<List<PurchasedBook>> findAllByCustomer(User user);
}
