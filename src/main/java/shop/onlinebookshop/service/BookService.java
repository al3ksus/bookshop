package shop.onlinebookshop.service;

import java.util.*;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import shop.onlinebookshop.models.books.Book;
import shop.onlinebookshop.models.users.User;
import shop.onlinebookshop.repository.BookRepository;
import shop.onlinebookshop.repository.PurchasedBookRepository;
import shop.onlinebookshop.repository.UserRepository;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PurchasedBookRepository purchasedBookRepository;

    public boolean isBookTitleEmpty(String title) {
        return title.isBlank();
    }

    public void addBook(String title, String genre, String authorName, User user) {
        Book book = new Book(title, genre.toLowerCase(), authorName, user);
        bookRepository.save(book);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }


    public Book getBookById(Long id) {
        return bookRepository.getById(id);
    }

    public boolean bookExistsById(String id) {
        try {
            if (!bookRepository.existsById(Long.parseLong(id))) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Map<String, Object> getAuthorStats(String login) {
        Map<String, Object> stats = new HashMap<>();
        Optional<List<Book>> books = bookRepository.findAllByAuthor(userRepository.getByLogin(login));
        stats.put("books", books.orElse(List.of()));
        stats.put("countBooks", books.orElse(List.of()).size());


        int totalCount = 0;
        List<Map<String, Object>> purchasedBooks = new ArrayList<>();

        for (Book book: books.orElse(List.of())) {
            int count = purchasedBookRepository.findAllByBook(book).orElse(List.of()).size();
            if(count != 0) {
                purchasedBooks.add(Map.of("book", book, "count", count));
                totalCount += count;
            }
        }

        stats.put("purchasedBooks", purchasedBooks);
        stats.put("countPurchasedBooks", totalCount);

        return stats;
    }
}
