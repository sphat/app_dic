package fr.paris.lutece.plugins.rokmeul.business.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.paris.lutece.plugins.rokmeul.business.dal.Post;
import fr.paris.lutece.plugins.rokmeul.business.dao.intf.IPostDAO;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.admin.AdminUserService;
import fr.paris.lutece.portal.service.jpa.JPALuteceCoreDAO;

/**
 * @author Sereivuth PHAT
 *
 */
@Repository
@Transactional(transactionManager = "transactionManager")
public class PostDAO extends JPALuteceCoreDAO<Integer, Post> implements IPostDAO {

    @Override
    public String getPluginName() {
        return "rokmeul";
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.paris.lutece.plugins.rokmeul.business.dao.intf.IPostDAO#insert(fr.
     * paris.lutece.plugins.rokmeul.business.dal.Post,
     * javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void insert(Post post, HttpServletRequest request) {
        AdminUser adminUser = AdminUserService.getAdminUser(request);
        if (adminUser != null) {
            post.setCreationUser(adminUser.getAccessCode());
        }
        super.create(post);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.paris.lutece.plugins.rokmeul.business.dao.intf.IPostDAO#update(fr.
     * paris.lutece.plugins.rokmeul.business.dal.Post,
     * javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void update(Post post, HttpServletRequest request) {
        long time = new java.util.Date().getTime();
        Timestamp modificationDate = new Timestamp(time);
        post.setModificationDate(modificationDate);

        AdminUser adminUser = AdminUserService.getAdminUser(request);
        if (adminUser != null) {
            post.setModificationUser(adminUser.getAccessCode());
        }

        super.update(post);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.paris.lutece.plugins.rokmeul.business.dao.intf.IPostDAO#delete(int,
     * javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void delete(int nKey, HttpServletRequest request) {
        super.remove(nKey);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.plugins.rokmeul.business.dao.intf.IPostDAO#load(int,
     * javax.servlet.http.HttpServletRequest)
     */
    @Override
    public Post findByPkey(int nKey, HttpServletRequest request) {
        return super.findById(nKey);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.plugins.rokmeul.business.dao.intf.IPostDAO#
     * selectPostsList(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public List<Post> selectPostsList(HttpServletRequest request) {
        return super.findAll();
    }

}
