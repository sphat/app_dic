package fr.paris.lutece.plugins.rokmeul.business.dal;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sereivuth PHAT
 *
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = -308144837885619065L;

    AbstractEntity() {
        long time = new java.util.Date().getTime();
        creationDate = new Timestamp(time);
    }

    @Column(name = "creation_date", nullable = false)
    private Timestamp creationDate;

    @Column(name = "modification_date", nullable = true)
    private Timestamp modificationDate;

    @Column(name = "creation_user", length = 50, nullable = false)
    private String creationUser;

    @Column(name = "modification_user", length = 50, nullable = true)
    private String modificationUser;
}
