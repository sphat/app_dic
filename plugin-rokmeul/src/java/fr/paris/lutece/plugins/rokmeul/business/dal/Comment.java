package fr.paris.lutece.plugins.rokmeul.business.dal;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "rokmeul_comment")
@NoArgsConstructor
public class Comment extends AbstractEntity {

    private static final long serialVersionUID = -5821646879473224144L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", unique = true, nullable = false)
    private int id;

    @Column(name = "comment", nullable = false, length = 2048)
    private String comment;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "blog_reader_id")
    private BlogReader blogReader;

    // references
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comment")
    private Set<CommentReply> commentReplies = new HashSet<>(0);
}
