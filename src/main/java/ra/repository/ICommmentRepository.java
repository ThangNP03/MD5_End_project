package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.entity.Comment;
@Repository
public interface ICommmentRepository extends JpaRepository<Comment, Long > {
}
