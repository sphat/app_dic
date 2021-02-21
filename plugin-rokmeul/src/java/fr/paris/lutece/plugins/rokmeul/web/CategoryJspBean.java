package fr.paris.lutece.plugins.rokmeul.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.rokmeul.business.dal.Category;
import fr.paris.lutece.plugins.rokmeul.business.dao.CategoryHome;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;

/**
 * @author Sereivuth PHAT
 *
 */
@Controller(controllerJsp = "ManageCategory.jsp", controllerPath = "jsp/admin/plugins/rokmeul/", right = "ROKMEUL_CATEGORY_MANAGEMENT")
public class CategoryJspBean extends AbstractManageJspBean {

    private static final long serialVersionUID = 1867864540640733900L;

    // Templates
    private static final String TEMPLATE_MANAGE_CATEGORY = "/admin/plugins/rokmeul/manage_category.html";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_CATEGORY = "rokmeul.manage_category.pageTitle";

    // Markers
    private static final String MARK_CATEGORY_LIST = "category_list";
    private static final String MARK_CATEGORY = "category";

    private static final String JSP_MANAGE_CATEGORY = "jsp/admin/plugins/rokmeul/ManageCategory.jsp";

    // Views
    private static final String VIEW_MANAGE_CATEGORY = "manageCategory";

    // Session variable to store working values
    private Category iCategory;

    /**
     * Build the Manage View
     * 
     * @param request
     *            The HTTP request
     * @return The page
     */
    @View(value = VIEW_MANAGE_CATEGORY, defaultView = true)
    public String getManagePostss(HttpServletRequest request) {
        iCategory = null;
        List<Category> listCategory = CategoryHome.findAll(request);
        Map<String, Object> model = getPaginatedListModel(request, MARK_CATEGORY_LIST, listCategory,
                JSP_MANAGE_CATEGORY);

        return getPage(PROPERTY_PAGE_TITLE_MANAGE_CATEGORY, TEMPLATE_MANAGE_CATEGORY, model);
    }
}
