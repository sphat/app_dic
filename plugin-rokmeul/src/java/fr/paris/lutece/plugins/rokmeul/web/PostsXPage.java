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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.rokmeul.business.dal.Post;
import fr.paris.lutece.plugins.rokmeul.business.dao.PostHome;
import fr.paris.lutece.portal.service.admin.AccessDeniedException;
import fr.paris.lutece.portal.service.message.SiteMessage;
import fr.paris.lutece.portal.service.message.SiteMessageException;
import fr.paris.lutece.portal.service.message.SiteMessageService;
import fr.paris.lutece.portal.service.security.SecurityTokenService;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.util.mvc.xpage.MVCApplication;
import fr.paris.lutece.portal.util.mvc.xpage.annotations.Controller;
import fr.paris.lutece.portal.web.xpages.XPage;
import fr.paris.lutece.util.url.UrlItem;

/**
 * This class provides the user interface to manage Posts xpages ( manage,
 * create, modify, remove )
 */
@Controller(xpageName = "posts", pageTitleI18nKey = "rokmeul.xpage.posts.pageTitle", pagePathI18nKey = "rokmeul.xpage.posts.pagePathLabel")
public class PostsXPage extends MVCApplication {
    private static final long serialVersionUID = 2918123827061460684L;

    // Templates
    private static final String TEMPLATE_MANAGE_POSTSS = "/skin/plugins/rokmeul/manage_postss.html";
    private static final String TEMPLATE_CREATE_POSTS = "/skin/plugins/rokmeul/create_posts.html";
    private static final String TEMPLATE_MODIFY_POSTS = "/skin/plugins/rokmeul/modify_posts.html";

    // Parameters
    private static final String PARAMETER_ID_POSTS = "id";

    // Markers
    private static final String MARK_POSTS_LIST = "posts_list";
    private static final String MARK_POSTS = "posts";

    // Message
    private static final String MESSAGE_CONFIRM_REMOVE_POSTS = "rokmeul.message.confirmRemovePosts";

    // Views
    private static final String VIEW_MANAGE_POSTSS = "managePostss";
    private static final String VIEW_CREATE_POSTS = "createPosts";
    private static final String VIEW_MODIFY_POSTS = "modifyPosts";

    // Actions
    private static final String ACTION_CREATE_POSTS = "createPosts";
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
     * return the form to manage postss
     * 
     * @param request
     *            The Http request
     * @return the html code of the list of postss
     */
    @View(value = VIEW_MANAGE_POSTSS, defaultView = true)
    public XPage getManagePostss(HttpServletRequest request) {
        iPost = null;
        Map<String, Object> model = getModel();
        model.put(MARK_POSTS_LIST, PostHome.findAll(request));

        return getXPage(TEMPLATE_MANAGE_POSTSS, request.getLocale(), model);
    }

    /**
     * Returns the form to create a posts
     *
     * @param request
     *            The Http request
     * @return the html code of the posts form
     */
    @View(VIEW_CREATE_POSTS)
    public XPage getCreatePosts(HttpServletRequest request) {
        iPost = (iPost != null) ? iPost : new Post();

        Map<String, Object> model = getModel();
        model.put(MARK_POSTS, iPost);
        model.put(SecurityTokenService.MARK_TOKEN,
                SecurityTokenService.getInstance().getToken(request, ACTION_CREATE_POSTS));

        return getXPage(TEMPLATE_CREATE_POSTS, request.getLocale(), model);
    }

    /**
     * Process the data capture form of a new posts
     *
     * @param request
     *            The Http Request
     * @return The Jsp URL of the process result
     * @throws AccessDeniedException
     */
    @Action(ACTION_CREATE_POSTS)
    public XPage doCreatePosts(HttpServletRequest request) throws AccessDeniedException {
        populate(iPost, request, getLocale(request));

        if (!SecurityTokenService.getInstance().validate(request, ACTION_CREATE_POSTS)) {
            throw new AccessDeniedException("Invalid security token");
        }

        // Check constraints
        if (!validateBean(iPost)) {
            return redirectView(request, VIEW_CREATE_POSTS);
        }

        PostHome.create(iPost, request);
        addInfo(INFO_POSTS_CREATED, getLocale(request));

        return redirectView(request, VIEW_MANAGE_POSTSS);
    }

    /**
     * Manages the removal form of a posts whose identifier is in the http
     * request
     *
     * @param request
     *            The Http request
     * @return the html code to confirm
     * @throws fr.paris.lutece.portal.service.message.SiteMessageException
     *             {@link fr.paris.lutece.portal.service.message.SiteMessageException}
     */
    @Action(ACTION_CONFIRM_REMOVE_POSTS)
    public XPage getConfirmRemovePosts(HttpServletRequest request) throws SiteMessageException {
        int nId = Integer.parseInt(request.getParameter(PARAMETER_ID_POSTS));

        UrlItem url = new UrlItem(getActionFullUrl(ACTION_REMOVE_POSTS));
        url.addParameter(PARAMETER_ID_POSTS, nId);

        SiteMessageService.setMessage(request, MESSAGE_CONFIRM_REMOVE_POSTS, SiteMessage.TYPE_CONFIRMATION,
                url.getUrl());
        return null;
    }

    /**
     * Handles the removal form of a posts
     *
     * @param request
     *            The Http request
     * @return the jsp URL to display the form to manage postss
     */
    @Action(ACTION_REMOVE_POSTS)
    public XPage doRemovePosts(HttpServletRequest request) {
        int nId = Integer.parseInt(request.getParameter(PARAMETER_ID_POSTS));
        PostHome.removeById(nId, request);
        addInfo(INFO_POSTS_REMOVED, getLocale(request));

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
    public XPage getModifyPosts(HttpServletRequest request) {
        int nId = Integer.parseInt(request.getParameter(PARAMETER_ID_POSTS));

        if (iPost == null || (iPost.getId() != nId)) {
            iPost = PostHome.findById(nId, request);
        }

        Map<String, Object> model = getModel();
        model.put(MARK_POSTS, iPost);
        model.put(SecurityTokenService.MARK_TOKEN,
                SecurityTokenService.getInstance().getToken(request, ACTION_MODIFY_POSTS));

        return getXPage(TEMPLATE_MODIFY_POSTS, request.getLocale(), model);
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
    public XPage doModifyPosts(HttpServletRequest request) throws AccessDeniedException {
        populate(iPost, request, getLocale(request));

        if (!SecurityTokenService.getInstance().validate(request, ACTION_MODIFY_POSTS)) {
            throw new AccessDeniedException("Invalid security token");
        }

        // Check constraints
        if (!validateBean(iPost)) {
            return redirect(request, VIEW_MODIFY_POSTS, PARAMETER_ID_POSTS, iPost.getId());
        }

        PostHome.update(iPost, request);
        addInfo(INFO_POSTS_UPDATED, getLocale(request));

        return redirectView(request, VIEW_MANAGE_POSTSS);
    }
}
