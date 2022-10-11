package shop.onlinebookshop.service;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.onlinebookshop.models.books.Book;
import shop.onlinebookshop.models.books.PurchasedBook;
import shop.onlinebookshop.models.users.User;
import shop.onlinebookshop.repository.PurchasedBookRepository;
import shop.onlinebookshop.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PurchasedBookService {

    private final PurchasedBookRepository purchasedBookRepository;
    private final UserRepository userRepository;

    public void purchaseBook(User user, Book book) {
        purchasedBookRepository.save(new PurchasedBook(user, book));
    }

    public List<Map<String, Object>> getAll() {
        List<Map<String, Object>> books = new ArrayList<>();
        List<PurchasedBook> purchasedBooks = purchasedBookRepository.findAll();

        for (PurchasedBook purchasedBook : purchasedBooks) {
            books.add(Map.of(
                    "purchasedBook", purchasedBook,
                    "title", purchasedBook.getBook().getTitle(),
                    "authorName", purchasedBook.getBook().getAuthorName(),
                    "genre", purchasedBook.getBook().getGenre()
            ));
        }

        return books;
    }

    public List<Map<String, Object>> getAllByLogin(String login) {
        List<Map<String, Object>> books = new ArrayList<>();
        Optional<List<PurchasedBook>> booksOptional = purchasedBookRepository.findAllByCustomer(userRepository.getByLogin(login));

        for (PurchasedBook purchasedBook : booksOptional.orElse(List.of())) {
            books.add(Map.of(
                    "purchasedBook", purchasedBook,
                    "title", purchasedBook.getBook().getTitle(),
                    "authorName", purchasedBook.getBook().getAuthorName(),
                    "genre", purchasedBook.getBook().getGenre()
            ));
        }

        return books;
    }
}
