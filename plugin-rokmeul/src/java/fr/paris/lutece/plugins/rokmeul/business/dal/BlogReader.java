package fr.paris.lutece.plugins.rokmeul.business.dal;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "rokmeul_blog_reader")
@NoArgsConstructor
public class BlogReader extends AbstractEntity {

    private static final long serialVersionUID = 4281933964003720599L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reader_id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "activated", nullable = false)
    private boolean activated;

    @Column(name = "pwd", nullable = false, length = 255)
    private String pwd;

    @Column(name = "reset_pwd", nullable = false)
    private boolean resetPwd;

    @Column(name = "reset_pwd_token", nullable = false, length = 255)
    private String resetPwdToken;

    @Column(name = "reset_pwd_count", nullable = false)
    private int resetPwdCount;

    @Column(name = "reset_pwd_date", nullable = true)
    private Timestamp resetPwdDate;

    // references
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "blogReader")
    private Set<Comment> comments = new HashSet<>(0);

    // references
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "blogReader")
    private Set<CommentReply> commentReplies = new HashSet<>(0);
}
