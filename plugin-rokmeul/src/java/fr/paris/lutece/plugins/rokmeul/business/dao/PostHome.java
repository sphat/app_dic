package fr.paris.lutece.plugins.rokmeul.business.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.rokmeul.business.dal.Post;
import fr.paris.lutece.plugins.rokmeul.business.dao.intf.IPostDAO;
import fr.paris.lutece.portal.service.spring.SpringContextService;

/**
 * @author Sereivuth PHAT
 *
 */
public final class PostHome {
    // Static variable pointed at the DAO instance
    private static IPostDAO iPostDAO = SpringContextService.getBean("rokmeul.postDAO");

    /**
     * Private constructor - this class need not be instantiated
     */
    private PostHome() {
    }

    /**
     * Insert a new record in the table.
     * 
     * @param iPost
     *            Post
     * @param request
     *            the HttpServletRequest
     */
    public static Post create(Post iPost, HttpServletRequest request) {
        iPostDAO.insert(iPost, request);
        return iPost;
    }

    /**
     * Update the record in the table.
     * 
     * @param iPost
     *            Post
     * @param request
     *            the HttpServletRequest
     */
    public static Post update(Post iPost, HttpServletRequest request) {
        iPostDAO.update(iPost, request);
        return iPost;
    }

    /**
     * Remove a record from a given primary key.
     * 
     * @param key
     * @param request
     *            the HttpServletRequest
     */
    public static void removeById(int key, HttpServletRequest request) {
        iPostDAO.delete(key, request);
    }

    /**
     * Find a record from a given primary key.
     * 
     * @param key
     * @param request
     *            the HttpServletRequest
     * @return
     */
    public static Post findById(int key, HttpServletRequest request) {
        return iPostDAO.findByPkey(key, request);
    }

    /**
     * Find all record.
     * 
     * @param request
     *            the HttpServletRequest
     * @return
     */
    public static List<Post> findAll(HttpServletRequest request) {
        return iPostDAO.selectPostsList(request);
    }
}
