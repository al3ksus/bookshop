package shop.onlinebookshop.models.books;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.onlinebookshop.models.users.User;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String genre;

    private String authorName;

    @OneToOne
    private User author;

    public Book(String title, String genre, String authorName, User user) {
        this.title = title;
        this.genre = genre;
        this.authorName = authorName;
        this.author = user;
    }

}
