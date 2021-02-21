package fr.paris.lutece.plugins.rokmeul.business.dal;

import java.sql.Date;
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
@Table(name = "rokmeul_user")
@NoArgsConstructor
public class User extends AbstractEntity {

    private static final long serialVersionUID = 6354388652138767587L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private int id;

    @Column(name = "reset_pwd", nullable = false)
    private boolean resetPwd;

    @Column(name = "activated", nullable = false)
    private boolean activated;

    @Column(name = "reset_pwd_count", nullable = true)
    private int resetPwdCount;

    @Column(name = "login", nullable = false, length = 255)
    private String login;

    @Column(name = "pwd", nullable = false, length = 255)
    private String pwd;

    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @Column(name = "family_name", nullable = false, length = 255)
    private String familyName;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "date_of_birth", nullable = false)
    private Date dob;

    // references
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Post> posts = new HashSet<>(0);

}
