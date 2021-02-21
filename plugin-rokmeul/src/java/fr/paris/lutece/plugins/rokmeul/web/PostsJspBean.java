/*
 * Copyright (c) 2002-2020, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */

package fr.paris.lutece.plugins.rokmeul.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import fr.paris.lutece.plugins.rokmeul.business.dal.Post;
import fr.paris.lutece.plugins.rokmeul.business.dao.PostHome;
import fr.paris.lutece.portal.service.admin.AccessDeniedException;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.security.SecurityTokenService;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.util.url.UrlItem;

/**
 * This class provides the user interface to manage Posts features ( manage,
 * create, modify, remove )
 */
@Controller(controllerJsp = "ManagePostss.jsp", controllerPath = "jsp/admin/plugins/rokmeul/", right = "ROKMEUL_MANAGEMENT")
public class PostsJspBean extends AbstractManageJspBean {

    private static final long serialVersionUID = -478316067244596823L;
    // Templates
    private static final String TEMPLATE_MANAGE_POSTSS = "/admin/plugins/rokmeul/manage_postss.html";
    private static final String TEMPLATE_CREATE_POST = "/admin/plugins/rokmeul/create_posts.html";
    private static final String TEMPLATE_MODIFY_POSTS = "/admin/plugins/rokmeul/modify_posts.html";

    // Parameters
    private static final String PARAMETER_ID_POSTS = "id";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_POSTSS = "rokmeul.manage_postss.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_POSTS = "rokmeul.modify_posts.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_POST = "rokmeul.create_posts.pageTitle";

    // Markers
    private static final String MARK_POSTS_LIST = "posts_list";
    private static final String MARK_POST = "post";

    private static final String JSP_MANAGE_POSTSS = "jsp/admin/plugins/rokmeul/ManagePostss.jsp";

    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_POSTS = "rokmeul.message.confirmRemovePosts";

    // Validations
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "rokmeul.model.entity.posts.attribute.";

    // Views
    private static final String VIEW_MANAGE_POSTSS = "managePostss";
    private static final String VIEW_CREATE_POST = "createPosts";
    private static final String VIEW_MODIFY_POSTS = "modifyPosts";

    // Actions
    private static final String ACTION_CREATE_POST = "createPosts";
    private static final String ACTION_MODIFY_POSTS = "modifyPosts";
    private static final String ACTION_REMOVE_POSTS = "removePosts";
    private static final String ACTION_CONFIRM_REMOVE_POSTS = "confirmRemovePosts";

    // Infos
    private static final String INFO_POSTS_CREATED = "rokmeul.info.posts.created";
    private static final String INFO_POSTS_UPDATED = "rokmeul.info.posts.updated";
    private static final String INFO_POSTS_REMOVED = "rokmeul.info.posts.removed";

    // Session variable to store working values
    private Post iPost;

    /**
     * Build the Manage View
     * 
     * @param request
     *            The HTTP request
     * @return The page
     */
    @View(value = VIEW_MANAGE_POSTSS, defaultView = true)
    public String getManagePostss(HttpServletRequest request) {
        iPost = null;
        List<Post> listPostss = PostHome.findAll(request);
        Map<String, Object> model = getPaginatedListModel(request, MARK_POSTS_LIST, listPostss, JSP_MANAGE_POSTSS);

        return getPage(PROPERTY_PAGE_TITLE_MANAGE_POSTSS, TEMPLATE_MANAGE_POSTSS, model);
    }
    
    /**
     * Build the Manage View
     * 
     * @param request
     *            The HTTP request
     * @return The page
     */
    @View(value = VIEW_MANAGE_POSTSS, defaultView = true)
    public String getManagePostssV1(HttpServletRequest request) {
        iPost = null;
        List<Post> listPostss = PostHome.findAll(request);
        Map<String, Object> model = getPaginatedListModel(request, MARK_POSTS_LIST, listPostss, JSP_MANAGE_POSTSS);

        return getPage(PROPERTY_PAGE_TITLE_MANAGE_POSTSS, TEMPLATE_MANAGE_POSTSS, model);
    }

    /**
     * Returns the form to create a posts
     *
     * @param request
     *            The Http request
     * @return the html code of the posts form
     */
    @View(VIEW_CREATE_POST)
    public String getCreatePosts(HttpServletRequest request) {
        iPost = (iPost != null) ? iPost : new Post();

        Map<String, Object> model = getModel();
        model.put(MARK_POST, iPost);
        model.put(SecurityTokenService.MARK_TOKEN,
                SecurityTokenService.getInstance().getToken(request, ACTION_CREATE_POST));

        return getPage(PROPERTY_PAGE_TITLE_CREATE_POST, TEMPLATE_CREATE_POST, model);
    }

    /**
     * Process the data capture form of a new posts
     *
     * @param request
     *            The Http Request
     * @return The Jsp URL of the process result
     * @throws AccessDeniedException
     */
    @Action(ACTION_CREATE_POST)
    public String doCreatePosts(HttpServletRequest request) throws AccessDeniedException {
        populate(iPost, request, getLocale());

        if (!SecurityTokenService.getInstance().validate(request, ACTION_CREATE_POST)) {
            throw new AccessDeniedException("Invalid security token");
        }

        // Check constraints
        if (!validateBean(iPost, VALIDATION_ATTRIBUTES_PREFIX)) {
            return redirectView(request, VIEW_CREATE_POST);
        }

        Parser parser = Parser.builder().build();
        Node documentText = parser.parse(iPost.getText());
        Node documentHeadText = parser.parse(iPost.getHeadText());

        HtmlRenderer renderer = HtmlRenderer.builder().build();

        iPost.setTextHtml(renderer.render(documentText));
        iPost.setHeadTextHtml(renderer.render(documentHeadText));

        PostHome.create(iPost, request);
        addInfo(INFO_POSTS_CREATED, getLocale());

        return redirectView(request, VIEW_MANAGE_POSTSS);
    }

    /**
     * Manages the removal form of a posts whose identifier is in the http
     * request
     *
     * @param request
     *            The Http request
     * @return the html code to confirm
     */
    @Action(ACTION_CONFIRM_REMOVE_POSTS)
    public String getConfirmRemovePosts(HttpServletRequest request) {
        int nId = Integer.parseInt(request.getParameter(PARAMETER_ID_POSTS));
        UrlItem url = new UrlItem(getActionUrl(ACTION_REMOVE_POSTS));
        url.addParameter(PARAMETER_ID_POSTS, nId);

        String strMessageUrl = AdminMessageService.getMessageUrl(request, MESSAGE_CONFIRM_REMOVE_POSTS, url.getUrl(),
                AdminMessage.TYPE_CONFIRMATION);

        return redirect(request, strMessageUrl);
    }

    /**
     * Handles the removal form of a posts
     *
     * @param request
     *            The Http request
     * @return the jsp URL to display the form to manage postss
     */
    @Action(ACTION_REMOVE_POSTS)
    public String doRemovePosts(HttpServletRequest request) {
        int nId = Integer.parseInt(request.getParameter(PARAMETER_ID_POSTS));
        PostHome.removeById(nId, request);
        addInfo(INFO_POSTS_REMOVED, getLocale());

        return redirectView(request, VIEW_MANAGE_POSTSS);
    }

    /**
     * Returns the form to update info about a posts
     *
     * @param request
     *            The Http request
     * @return The HTML form to update info
     */
    @View(VIEW_MODIFY_POSTS)
    public String getModifyPosts(HttpServletRequest request) {
        int nId = Integer.parseInt(request.getParameter(PARAMETER_ID_POSTS));

        if (iPost == null || (iPost.getId() != nId)) {
            iPost = PostHome.findById(nId, request);
        }

        Map<String, Object> model = getModel();
        model.put(MARK_POST, iPost);
        model.put(SecurityTokenService.MARK_TOKEN,
                SecurityTokenService.getInstance().getToken(request, ACTION_MODIFY_POSTS));

        return getPage(PROPERTY_PAGE_TITLE_MODIFY_POSTS, TEMPLATE_MODIFY_POSTS, model);
    }

    /**
     * Process the change form of a posts
     *
     * @param request
     *            The Http request
     * @return The Jsp URL of the process result
     * @throws AccessDeniedException
     */
    @Action(ACTION_MODIFY_POSTS)
    public String doModifyPosts(HttpServletRequest request) throws AccessDeniedException {
        populate(iPost, request, getLocale());

        if (!SecurityTokenService.getInstance().validate(request, ACTION_MODIFY_POSTS)) {
            throw new AccessDeniedException("Invalid security token");
        }

        // Check constraints
        if (!validateBean(iPost, VALIDATION_ATTRIBUTES_PREFIX)) {
            return redirect(request, VIEW_MODIFY_POSTS, PARAMETER_ID_POSTS, iPost.getId());
        }

        Parser parser = Parser.builder().build();
        Node documentText = parser.parse(iPost.getText());
        Node documentHeadText = parser.parse(iPost.getHeadText());

        HtmlRenderer renderer = HtmlRenderer.builder().build();

        iPost.setTextHtml(renderer.render(documentText));
        iPost.setHeadTextHtml(renderer.render(documentHeadText));

        PostHome.update(iPost, request);
        addInfo(INFO_POSTS_UPDATED, getLocale());

        return redirectView(request, VIEW_MANAGE_POSTSS);
    }
}
