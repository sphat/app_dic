package fr.paris.lutece.plugins.rokmeul.business.dao.intf;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.rokmeul.business.dal.Category;

/**
 * @author Sereivuth PHAT
 *
 */
public interface ICategoryDAO {
    /**
     * Insert a new record in the table.
     * 
     * @param category
     *            instance of the Posts object to insert
     * @param request
     *            the HttpServletRequest
     */
    void insert(Category category, HttpServletRequest request);

    /**
     * Update the record in the table
     * 
     * @param category
     *            the reference of the Post
     * @param request
     *            the HttpServletRequest
     */
    void update(Category category, HttpServletRequest request);

    /**
     * Delete a record from the table
     * 
     * @param nKey
     *            The identifier of the category to delete
     * @param request
     *            the HttpServletRequest
     */
    void delete(int nKey, HttpServletRequest request);

    /**
     * Load the data from the table
     * 
     * @param nKey
     *            The identifier of the category
     * @param request
     *            the HttpServletRequest
     * @return The instance of the posts
     */
    Category findByPkey(int nKey, HttpServletRequest request);

    /**
     * Load the data of all the posts objects and returns them as a list
     * 
     * @param request
     *            the HttpServletRequest
     * @return The list which contains the data of all the categories objects
     */
    List<Category> selectPostsList(HttpServletRequest request);
}
