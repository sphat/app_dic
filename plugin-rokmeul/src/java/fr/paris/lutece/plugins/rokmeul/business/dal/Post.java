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
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

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
@Table(name = "rokmeul_post")
@NoArgsConstructor
public class Post extends AbstractEntity {

    private static final long serialVersionUID = -611660238250081558L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", unique = true, nullable = false)
    private int id;

    @Column(name = "title", nullable = false, length = 255)
    @NotEmpty(message = "#i18n{rokmeul.validation.posts.Title.notEmpty}")
    @Size(max = 255, message = "#i18n{rokmeul.validation.posts.Title.size}")
    private String title;

    @Column(name = "text", nullable = false)
    @Type(type = "text")
    @NotEmpty(message = "#i18n{rokmeul.validation.posts.Text.notEmpty}")
    private String text;

    @Column(name = "head_text", nullable = false)
    @Type(type = "text")
    private String headText;

    @Column(name = "text_display_on_index", nullable = false)
    @Type(type = "text")
    private String textDisplayOnIndex;

    @Column(name = "image_display_on_index", nullable = false, length = 255)
    private String imageDisplayOnIndex;

    @Column(name = "display_on_index", nullable = false)
    private boolean displayOnIndex;

    @Column(name = "publish", nullable = false)
    private boolean publish;

    // HTML text generated from text
    @Column(name = "text_html", nullable = false)
    @Type(type = "text")
    private String textHtml;

    // HTML text generated from text
    @Column(name = "head_text_html", nullable = false)
    @Type(type = "text")
    private String headTextHtml;

    // references
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private Set<Image> images = new HashSet<>(0);

    // references
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private Set<Comment> comments = new HashSet<>(0);

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
