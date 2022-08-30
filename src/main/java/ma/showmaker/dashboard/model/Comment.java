package ma.showmaker.dashboard.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.yaml.snakeyaml.comments.CommentType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="ds_comment")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String comment;

    @Enumerated(EnumType.STRING)
    private CommentType type;

    @CreatedDate
    private Timestamp createdAt;

    @CreatedBy
    private String createdBy;
}
