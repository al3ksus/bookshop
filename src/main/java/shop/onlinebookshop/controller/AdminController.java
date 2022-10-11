package shop.onlinebookshop.controller;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.onlinebookshop.models.users.Role;
import shop.onlinebookshop.service.BookService;
import shop.onlinebookshop.service.PurchasedBookService;
import shop.onlinebookshop.service.UserService;
import shop.onlinebookshop.utils.Constant;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private static final String ADMIN_PASSWORD = "12345678";
    private final UserService userService;
    private final BookService bookService;
    private final PurchasedBookService purchasedBookService;

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }

    @PostMapping("/admin")
    public String logInAsAdmin(@RequestParam String password, Map<String, Object> model) {
        if (!ADMIN_PASSWORD.equals(password)) {
            model.put("error", Constant.ERROR_PASSWORD_MESSAGE);
            return "admin";
        }

        model.put("profit", purchasedBookService.getAll().size() * Constant.PRICE_FOR_PURCHASE - bookService.getAll().size() * Constant.PRICE_FOR_PUBLISH);
        return "admin-main-page";
    }

    @GetMapping("/admin/main")
    public String getAdminMainPage(Map<String, Object> model) {
        model.put("profit", purchasedBookService.getAll().size() * Constant.PRICE_FOR_PURCHASE - bookService.getAll().size() * Constant.PRICE_FOR_PUBLISH);

        return "admin-main-page";
    }

    @GetMapping("/admin/customer_list")
    public String getCustomerList(Map<String, Object> model) {
        model.put("customers", userService.getAllByRole(Role.CUSTOMER));
        return "admin-customer-list";
    }

    @GetMapping("/admin/author_list")
    public String getAuthorList(Map<String, Object> model) {
        model.put("authors", userService.getAllByRole(Role.AUTHOR));
        return "admin-author-list";
    }

    @GetMapping("/admin/book_list")
    public String getBookList(Map<String, Object> model) {
        model.put("books", bookService.getAll());
        return "admin-book-list";
    }

    @GetMapping("/admin/purchased_book_list")
    public String getPurchasedBookList(Map<String, Object> model) {
        model.put("books", purchasedBookService.getAll());
        return "admin-purchased-book-list";
    }
}
