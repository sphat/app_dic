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
@Table(name = "rokmeul_image")
@NoArgsConstructor
public class Image extends AbstractEntity {

    private static final long serialVersionUID = 5344638074502495761L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", unique = true, nullable = false)
    private int id;

    @Column(name = "image_size", nullable = false)
    private long imageSize;

    @Column(name = "image_name", nullable = false, length = 255)
    private String imageName;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
