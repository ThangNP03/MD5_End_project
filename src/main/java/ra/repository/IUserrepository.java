package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.entity.user.Users;
@Repository
public interface IUserrepository extends JpaRepository<Users, Long > {
    Users findByUsername(String username);
    boolean existsByUsername (String username);
    boolean existsByEmail(String email);


}
