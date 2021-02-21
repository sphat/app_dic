package fr.paris.lutece.plugins.rokmeul.business.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.paris.lutece.plugins.rokmeul.business.dal.Category;
import fr.paris.lutece.plugins.rokmeul.business.dao.intf.ICategoryDAO;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.admin.AdminUserService;
import fr.paris.lutece.portal.service.jpa.JPALuteceCoreDAO;

/**
 * @author Sereivuth PHAT
 *
 */
@Repository
@Transactional(transactionManager = "transactionManager")
public class CategoryDAO extends JPALuteceCoreDAO<Integer, Category> implements ICategoryDAO {

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.paris.lutece.plugins.rokmeul.business.dao.intf.ICategoryDAO#insert(fr.
     * paris.lutece.plugins.rokmeul.business.dal.Category,
     * javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void insert(Category category, HttpServletRequest request) {
        AdminUser adminUser = AdminUserService.getAdminUser(request);
        if (adminUser != null) {
            category.setCreationUser(adminUser.getAccessCode());
        }
        super.create(category);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.paris.lutece.plugins.rokmeul.business.dao.intf.ICategoryDAO#update(fr.
     * paris.lutece.plugins.rokmeul.business.dal.Category,
     * javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void update(Category category, HttpServletRequest request) {
        long time = new java.util.Date().getTime();
        Timestamp modificationDate = new Timestamp(time);
        category.setModificationDate(modificationDate);

        AdminUser adminUser = AdminUserService.getAdminUser(request);
        if (adminUser != null) {
            category.setModificationUser(adminUser.getAccessCode());
        }

        super.update(category);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.paris.lutece.plugins.rokmeul.business.dao.intf.ICategoryDAO#delete(
     * int, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void delete(int nKey, HttpServletRequest request) {
        super.remove(nKey);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.paris.lutece.plugins.rokmeul.business.dao.intf.ICategoryDAO#findByPkey
     * (int, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public Category findByPkey(int nKey, HttpServletRequest request) {
        return super.findById(nKey);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.plugins.rokmeul.business.dao.intf.ICategoryDAO#
     * selectPostsList(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public List<Category> selectPostsList(HttpServletRequest request) {
        return super.findAll();
    }

}
