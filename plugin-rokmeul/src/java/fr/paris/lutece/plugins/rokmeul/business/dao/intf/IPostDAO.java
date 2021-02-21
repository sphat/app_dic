package fr.paris.lutece.plugins.rokmeul.business.dao.intf;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.rokmeul.business.dal.Post;

/**
 * @author Sereivuth PHAT
 *
 */
public interface IPostDAO {
    /**
     * Insert a new record in the table.
     * 
     * @param post
     *            instance of the Posts object to insert
     * @param request
     *            the HttpServletRequest
     */
    void insert(Post post, HttpServletRequest request);

    /**
     * Update the record in the table
     * 
     * @param post
     *            the reference of the Post
     * @param request
     *            the HttpServletRequest
     */
    void update(Post post, HttpServletRequest request);

    /**
     * Delete a record from the table
     * 
     * @param nKey
     *            The identifier of the Post to delete
     * @param request
     *            the HttpServletRequest
     */
    void delete(int nKey, HttpServletRequest request);

    /**
     * Load the data from the table
     * 
     * @param nKey
     *            The identifier of the post
     * @param request
     *            the HttpServletRequest
     * @return The instance of the posts
     */
    Post findByPkey(int nKey, HttpServletRequest request);

    /**
     * Load the data of all the posts objects and returns them as a list
     * 
     * @param request
     *            the HttpServletRequest
     * @return The list which contains the data of all the posts objects
     */
    List<Post> selectPostsList(HttpServletRequest request);
}
