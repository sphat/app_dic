package fr.paris.lutece.plugins.rokmeul.business.dal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Sereivuth PHAT
 *
 */
@Getter
@Setter
@Entity
@Table(name = "rokmeul_comment_reply")
@NoArgsConstructor
public class CommentReply extends AbstractEntity {

    private static final long serialVersionUID = 8963744366527070301L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id", unique = true, nullable = false)
    private int id;

    @Column(name = "comment_reply", nullable = false, length = 2048)
    private String commentReply;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "blog_reader_id")
    private BlogReader blogReader;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;
}
