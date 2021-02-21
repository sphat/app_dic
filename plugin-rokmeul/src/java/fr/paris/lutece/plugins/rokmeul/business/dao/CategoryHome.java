package fr.paris.lutece.plugins.rokmeul.business.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.rokmeul.business.dal.Category;
import fr.paris.lutece.plugins.rokmeul.business.dao.intf.ICategoryDAO;
import fr.paris.lutece.portal.service.spring.SpringContextService;

/**
 * @author Sereivuth PHAT
 *
 */
public final class CategoryHome {
    // Static variable pointed at the DAO instance
    private static ICategoryDAO iCategoryDAO = SpringContextService.getBean("rokmeul.categoryDAO");

    /**
     * Private constructor - this class need not be instantiated
     */
    private CategoryHome() {
    }

    /**
     * Insert a new record in the table.
     * 
     * @param Category
     *            Category
     * @param request
     *            the HttpServletRequest
     */
    public static Category create(Category iCategory, HttpServletRequest request) {
        iCategoryDAO.insert(iCategory, request);
        return iCategory;
    }

    /**
     * Update the record in the table.
     * 
     * @param iCategory
     *            Category
     * @param request
     *            the HttpServletRequest
     */
    public static Category update(Category iCategory, HttpServletRequest request) {
        iCategoryDAO.update(iCategory, request);
        return iCategory;
    }

    /**
     * Remove a record from a given primary key.
     * 
     * @param key
     * @param request
     *            the HttpServletRequest
     */
    public static void removeById(int key, HttpServletRequest request) {
        iCategoryDAO.delete(key, request);
    }

    /**
     * Find a record from a given primary key.
     * 
     * @param key
     * @param request
     *            the HttpServletRequest
     * @return
     */
    public static Category findById(int key, HttpServletRequest request) {
        return iCategoryDAO.findByPkey(key, request);
    }

    /**
     * Find all record.
     * 
     * @param request
     *            the HttpServletRequest
     * @return
     */
    public static List<Category> findAll(HttpServletRequest request) {
        return iCategoryDAO.selectPostsList(request);
    }
}
