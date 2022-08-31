package ma.showmaker.dashboard.repository;

import ma.showmaker.dashboard.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where year(c.createdAt) = ?1 and month(c.createdAt) = ?2" +
            "and day(c.createdAt) = ?3")
    List<Comment> findByCreatedYearAndMonthAndDay(int year, int month, int day);
}
