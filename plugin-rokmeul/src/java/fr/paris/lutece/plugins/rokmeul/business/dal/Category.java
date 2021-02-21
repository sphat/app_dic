package fr.paris.lutece.plugins.rokmeul.business.dal;

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
@Table(name = "rokmeul_category")
@NoArgsConstructor
public class Category extends AbstractEntity {

    private static final long serialVersionUID = 7200608441787213491L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", unique = true, nullable = false)
    private int id;
    
    /**
     * Category name
     */
    private String name;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", nullable = false, length = 1024)
    private String description;

    @Column(name = "status", nullable = false)
    private boolean status;

    // references
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<Post> posts = new HashSet<>(0);

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
