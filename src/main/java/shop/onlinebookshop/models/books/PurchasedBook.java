package shop.onlinebookshop.models.books;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.onlinebookshop.models.users.User;

@Entity
@Table(name = "purchased_book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @OneToOne
    private Book book;

    public PurchasedBook(User customer, Book book){
        this.customer = customer;
        this.book = book;
    }

}
