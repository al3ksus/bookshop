package shop.onlinebookshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.onlinebookshop.models.users.Role;
import shop.onlinebookshop.models.users.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByRole(Role role);
    Optional<User> findByLogin(String login);
    User getByLogin(String login);
    boolean existsByLogin(String login);

}
