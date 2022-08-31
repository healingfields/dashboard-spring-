package ma.showmaker.dashboard.service;

import ma.showmaker.dashboard.model.Comment;
import ma.showmaker.dashboard.model.CommentType;
import ma.showmaker.dashboard.repository.CommentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Array;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CommentServiceTest {

    @MockBean
    private CommentRepository commentRepository;

    private CommentService commentService;

    @Before
    public void init(){
        commentService = new CommentService(commentRepository);
    }

    @Test
    public void getAllCommentsForToday_HappyPath_ShouldReturn1Comment(){

        Comment comment = new Comment();
        comment.setComment("test");
        comment.setType(CommentType.STAR);
        comment.setCreatedAt(new Timestamp(
                System.currentTimeMillis()
        ));
        List<Comment> comments = Arrays.asList(comment);
        LocalDate now = LocalDate.now();

        when(commentRepository.findByCreatedYearAndMonthAndDay(now.getYear(), now.getMonth().getValue(), now.getDayOfMonth())).thenReturn(comments);

        List<Comment> actualComments = commentService.getAllCommentsForToday();

        verify(commentRepository, times(1)).findByCreatedYearAndMonthAndDay(now.getYear(), now.getMonth().getValue(), now.getDayOfMonth());
        assertThat(comments).isEqualTo(actualComments);
    }

    @Test
    public void savedAll_HappyPath_ShouldSave2Comments(){

        Comment comment = new Comment();
        comment.setComment("test1");
        comment.setType(CommentType.DELTA);
        comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        Comment comment2 = new Comment();
        comment2.setComment("test2");
        comment2.setType(CommentType.PLUS);
        comment2.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        List<Comment> comments = Arrays.asList(comment, comment2);

        when(commentRepository.saveAll(comments)).thenReturn(comments);

        List<Comment> savedComments = commentService.saveAll(comments);

        assertThat(savedComments).isNotEmpty();
        verify(commentRepository, times(1)).saveAll(comments);
    }

}
