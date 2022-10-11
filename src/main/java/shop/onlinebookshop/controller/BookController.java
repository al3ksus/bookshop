package shop.onlinebookshop.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.onlinebookshop.models.books.Book;
import shop.onlinebookshop.models.users.User;
import shop.onlinebookshop.service.BookService;
import shop.onlinebookshop.service.PurchasedBookService;
import shop.onlinebookshop.service.UserService;
import shop.onlinebookshop.utils.Constant;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final UserService userService;
    private final PurchasedBookService purchasedBookService;

    @GetMapping("/author/publish/{login}")
    public String getPublishPage(@PathVariable String login, Map<String, Object> model) {
        model.put("user", userService.getUserByLogin(login));
        model.put("price", Constant.PRICE_FOR_PUBLISH);
        return "publish-book";
    }

    @PostMapping("/author/publish/{login}")
    public String publish(@PathVariable String login, @RequestParam String title, @RequestParam Map<String, String> genre, Map<String, Object> model) {
        User user = userService.getUserByLogin(login);
        model.put("user", user);
        model.put("price", Constant.PRICE_FOR_PUBLISH);

        if (bookService.isBookTitleEmpty(title)) {
            model.put("error", Constant.EMPTY_TITLE_FIELDS_MESSAGE);
            return "publish-book";
        }

        bookService.addBook(title, genre.get("genre"), user.getAuthorInfo(), user);
        model.put("notification", Constant.SUCCESS_PUBLISH_MESSAGE);

        return "publish-book";
    }

    @GetMapping("/customer/buy/{login}")
    public String getBuyPage(@PathVariable String login, Map<String, Object> model) {
        model.put("books", bookService.getAll());
        model.put("user", userService.getUserByLogin(login));
        model.put("price", Constant.PRICE_FOR_PURCHASE);
        return "buy";
    }

    @PostMapping("/customer/buy/{login}")
    public String buy(@PathVariable String login, @RequestParam String bookId, Map<String, Object> model) {
        User user = userService.getUserByLogin(login);
        model.put("books", bookService.getAll());
        model.put("user", user);
        model.put("price", Constant.PRICE_FOR_PURCHASE);

        if (!bookService.bookExistsById(bookId)) {
            model.put("error", Constant.ERROR_ID_MESSAGE);
            return "buy";
        }

        Book book = bookService.getBookById(Long.parseLong(bookId));
        purchasedBookService.purchaseBook(user, book);
        model.put("notification", Constant.SUCCESS_PURCHASE_MESSAGE);

        return "buy";
    }

    @GetMapping("/author/stats/{login}")
    public String getAuthorStats(@PathVariable String login, Map<String, Object> model) {
        model.put("user", userService.getUserByLogin(login));

        Map<String, Object> stats = bookService.getAuthorStats(login);

        model.put("books", stats.get("books"));
        model.put("countBooks", stats.get("countBooks"));
        model.put("purchasedBooks", stats.get("purchasedBooks"));
        model.put("countPurchasedBooks", stats.get("countPurchasedBooks"));
        model.put("profit", (Integer) stats.get("countBooks") * Constant.PRICE_FOR_PUBLISH);

        return "author-stats";
    }

    @GetMapping("/customer/books/{login}")
    public String getCustomerBooks(@PathVariable String login, Map<String, Object> model) {
        model.put("user", userService.getUserByLogin(login));
        model.put("books", purchasedBookService.getAllByLogin(login));

        return "customer-book-list";
    }
}
