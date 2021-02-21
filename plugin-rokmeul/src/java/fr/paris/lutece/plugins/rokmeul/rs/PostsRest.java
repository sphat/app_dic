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

package fr.paris.lutece.plugins.rokmeul.rs;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import fr.paris.lutece.plugins.rest.service.RestConstants;
import fr.paris.lutece.plugins.rokmeul.business.dal.Post;
import fr.paris.lutece.plugins.rokmeul.business.dao.PostHome;
import fr.paris.lutece.util.json.ErrorJsonResponse;
import fr.paris.lutece.util.json.JsonResponse;
import fr.paris.lutece.util.json.JsonUtil;

/**
 * PostsRest
 */
@Path(RestConstants.BASE_PATH + Constants.API_PATH + Constants.VERSION_PATH + Constants.POSTS_PATH)
public class PostsRest {
    private static final int VERSION_1 = 1;
    private final Logger _logger = Logger.getLogger(RestConstants.REST_LOGGER);

    /**
     * Get Posts List
     * 
     * @param nVersion
     *            the API version
     * @return the Posts List
     */
    @GET
    @Path(StringUtils.EMPTY)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostsList(@PathParam(Constants.VERSION) Integer nVersion) {
        if (nVersion == VERSION_1) {
            return getPostsListV1();
        }
        _logger.error(Constants.ERROR_NOT_FOUND_VERSION);
        return Response.status(Response.Status.NOT_FOUND)
                .entity(JsonUtil.buildJsonResponse(
                        new ErrorJsonResponse(Response.Status.NOT_FOUND.name(), Constants.ERROR_NOT_FOUND_VERSION)))
                .build();
    }

    /**
     * Get Posts List V1
     * 
     * @return the Posts List for the version 1
     */
    private Response getPostsListV1() {
        List<Post> listPostss = PostHome.findAll(null);

        if (listPostss.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity(JsonUtil.buildJsonResponse(new JsonResponse(Constants.EMPTY_OBJECT))).build();
        }
        return Response.status(Response.Status.OK).entity(JsonUtil.buildJsonResponse(new JsonResponse(listPostss)))
                .build();
    }

    /**
     * Create Posts
     * 
     * @param nVersion
     *            the API version
     * @param title
     *            the title
     * @param text
     *            the text
     * @param create_date
     *            the create_date
     * @param update_date
     *            the update_date
     * @param category_id
     *            the category_id
     * @param user_id
     *            the user_id
     * @param publish
     *            the publish
     * @param text_html
     *            the text_html
     * @return the Posts if created
     */
    @POST
    @Path(StringUtils.EMPTY)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPosts(@FormParam(Constants.POSTS_ATTRIBUTE_TITLE) String title,
            @FormParam(Constants.POSTS_ATTRIBUTE_TEXT) String text,
            @FormParam(Constants.POSTS_ATTRIBUTE_CREATE_DATE) String create_date,
            @FormParam(Constants.POSTS_ATTRIBUTE_UPDATE_DATE) String update_date,
            @FormParam(Constants.POSTS_ATTRIBUTE_CATEGORY_ID) String category_id,
            @FormParam(Constants.POSTS_ATTRIBUTE_USER_ID) String user_id,
            @FormParam(Constants.POSTS_ATTRIBUTE_PUBLISH) String publish,
            @FormParam(Constants.POSTS_ATTRIBUTE_TEXT_HTML) String text_html,
            @PathParam(Constants.VERSION) Integer nVersion) {
        if (nVersion == VERSION_1) {
            return createPostsV1(title, text, create_date, update_date, category_id, user_id, publish, text_html);
        }
        _logger.error(Constants.ERROR_NOT_FOUND_VERSION);
        return Response.status(Response.Status.NOT_FOUND)
                .entity(JsonUtil.buildJsonResponse(
                        new ErrorJsonResponse(Response.Status.NOT_FOUND.name(), Constants.ERROR_NOT_FOUND_VERSION)))
                .build();
    }

    /**
     * Create Posts V1
     * 
     * @param title
     *            the title
     * @param text
     *            the text
     * @param create_date
     *            the create_date
     * @param update_date
     *            the update_date
     * @param category_id
     *            the category_id
     * @param user_id
     *            the user_id
     * @param publish
     *            the publish
     * @param text_html
     *            the text_html
     * @return the Posts if created for the version 1
     */
    private Response createPostsV1(String title, String text, String create_date, String update_date,
            String category_id, String user_id, String publish, String text_html) {
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(text) || StringUtils.isEmpty(create_date)
                || StringUtils.isEmpty(update_date) || StringUtils.isEmpty(category_id) || StringUtils.isEmpty(user_id)
                || StringUtils.isEmpty(publish) || StringUtils.isEmpty(text_html)) {
            _logger.error(Constants.ERROR_BAD_REQUEST_EMPTY_PARAMETER);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(JsonUtil.buildJsonResponse(new ErrorJsonResponse(Response.Status.BAD_REQUEST.name(),
                            Constants.ERROR_BAD_REQUEST_EMPTY_PARAMETER)))
                    .build();
        }

        Post iPost = new Post();
        iPost.setTitle(title);
        iPost.setText(text);
        // iPost.setCreateDate(Date.valueOf(create_date));
        // iPost.setUpdateDate(Date.valueOf(update_date));
        // iPost.setCategoryId(Integer.parseInt(category_id));
        // iPost.setUserId(Integer.parseInt(user_id));
        // iPost.setPublish(Boolean.parseBoolean(publish));
        iPost.setTextHtml(text_html);
        PostHome.create(iPost, null);

        return Response.status(Response.Status.OK).entity(JsonUtil.buildJsonResponse(new JsonResponse(iPost))).build();
    }

    /**
     * Modify Posts
     * 
     * @param nVersion
     *            the API version
     * @param id
     *            the id
     * @param title
     *            the title
     * @param text
     *            the text
     * @param create_date
     *            the create_date
     * @param update_date
     *            the update_date
     * @param category_id
     *            the category_id
     * @param user_id
     *            the user_id
     * @param publish
     *            the publish
     * @param text_html
     *            the text_html
     * @return the Posts if modified
     */
    @PUT
    @Path(Constants.ID_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyPosts(@PathParam(Constants.ID) Integer id,
            @FormParam(Constants.POSTS_ATTRIBUTE_TITLE) String title,
            @FormParam(Constants.POSTS_ATTRIBUTE_TEXT) String text,
            @FormParam(Constants.POSTS_ATTRIBUTE_CREATE_DATE) String create_date,
            @FormParam(Constants.POSTS_ATTRIBUTE_UPDATE_DATE) String update_date,
            @FormParam(Constants.POSTS_ATTRIBUTE_CATEGORY_ID) String category_id,
            @FormParam(Constants.POSTS_ATTRIBUTE_USER_ID) String user_id,
            @FormParam(Constants.POSTS_ATTRIBUTE_PUBLISH) String publish,
            @FormParam(Constants.POSTS_ATTRIBUTE_TEXT_HTML) String text_html,
            @PathParam(Constants.VERSION) Integer nVersion) {
        if (nVersion == VERSION_1) {
            return modifyPostsV1(id, title, text, create_date, update_date, category_id, user_id, publish, text_html);
        }
        _logger.error(Constants.ERROR_NOT_FOUND_VERSION);
        return Response.status(Response.Status.NOT_FOUND)
                .entity(JsonUtil.buildJsonResponse(
                        new ErrorJsonResponse(Response.Status.NOT_FOUND.name(), Constants.ERROR_NOT_FOUND_VERSION)))
                .build();
    }

    /**
     * Modify Posts V1
     * 
     * @param id
     *            the id
     * @param title
     *            the title
     * @param text
     *            the text
     * @param create_date
     *            the create_date
     * @param update_date
     *            the update_date
     * @param category_id
     *            the category_id
     * @param user_id
     *            the user_id
     * @param publish
     *            the publish
     * @param text_html
     *            the text_html
     * @return the Posts if modified for the version 1
     */
    private Response modifyPostsV1(Integer id, String title, String text, String create_date, String update_date,
            String category_id, String user_id, String publish, String text_html) {
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(text) || StringUtils.isEmpty(create_date)
                || StringUtils.isEmpty(update_date) || StringUtils.isEmpty(category_id) || StringUtils.isEmpty(user_id)
                || StringUtils.isEmpty(publish) || StringUtils.isEmpty(text_html)) {
            _logger.error(Constants.ERROR_BAD_REQUEST_EMPTY_PARAMETER);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(JsonUtil.buildJsonResponse(new ErrorJsonResponse(Response.Status.BAD_REQUEST.name(),
                            Constants.ERROR_BAD_REQUEST_EMPTY_PARAMETER)))
                    .build();
        }

        Post iPost = PostHome.findById(id, null);
        if (iPost == null) {
            _logger.error(Constants.ERROR_NOT_FOUND_RESOURCE);
            return Response.status(Response.Status.NOT_FOUND).entity(JsonUtil.buildJsonResponse(
                    new ErrorJsonResponse(Response.Status.NOT_FOUND.name(), Constants.ERROR_NOT_FOUND_RESOURCE)))
                    .build();
        }

        iPost.setTitle(title);
        iPost.setText(text);
        // iPost.setCreateDate(Date.valueOf(create_date));
        // iPost.setUpdateDate(Date.valueOf(update_date));
        // iPost.setCategoryId(Integer.parseInt(category_id));
        // iPost.setUserId(Integer.parseInt(user_id));
        // iPost.setPublish(Boolean.parseBoolean(publish));
        iPost.setTextHtml(text_html);
        PostHome.update(iPost, null);

        return Response.status(Response.Status.OK).entity(JsonUtil.buildJsonResponse(new JsonResponse(iPost))).build();
    }

    /**
     * Delete Posts
     * 
     * @param nVersion
     *            the API version
     * @param id
     *            the id
     * @return the Posts List if deleted
     */
    @DELETE
    @Path(Constants.ID_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePosts(@PathParam(Constants.VERSION) Integer nVersion, @PathParam(Constants.ID) Integer id) {
        if (nVersion == VERSION_1) {
            return deletePostsV1(id);
        }
        _logger.error(Constants.ERROR_NOT_FOUND_VERSION);
        return Response.status(Response.Status.NOT_FOUND)
                .entity(JsonUtil.buildJsonResponse(
                        new ErrorJsonResponse(Response.Status.NOT_FOUND.name(), Constants.ERROR_NOT_FOUND_VERSION)))
                .build();
    }

    /**
     * Delete Posts V1
     * 
     * @param id
     *            the id
     * @return the Posts List if deleted for the version 1
     */
    private Response deletePostsV1(Integer id) {
        Post iPost = PostHome.findById(id, null);
        if (iPost == null) {
            _logger.error(Constants.ERROR_NOT_FOUND_RESOURCE);
            return Response.status(Response.Status.NOT_FOUND).entity(JsonUtil.buildJsonResponse(
                    new ErrorJsonResponse(Response.Status.NOT_FOUND.name(), Constants.ERROR_NOT_FOUND_RESOURCE)))
                    .build();
        }

        PostHome.removeById(id, null);

        return Response.status(Response.Status.OK)
                .entity(JsonUtil.buildJsonResponse(new JsonResponse(Constants.EMPTY_OBJECT))).build();
    }

    /**
     * Get Posts
     * 
     * @param nVersion
     *            the API version
     * @param id
     *            the id
     * @return the Posts
     */
    @GET
    @Path(Constants.ID_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPosts(@PathParam(Constants.VERSION) Integer nVersion, @PathParam(Constants.ID) Integer id) {
        if (nVersion == VERSION_1) {
            return getPostsV1(id);
        }
        _logger.error(Constants.ERROR_NOT_FOUND_VERSION);
        return Response.status(Response.Status.NOT_FOUND)
                .entity(JsonUtil.buildJsonResponse(
                        new ErrorJsonResponse(Response.Status.NOT_FOUND.name(), Constants.ERROR_NOT_FOUND_VERSION)))
                .build();
    }

    /**
     * Get Posts V1
     * 
     * @param id
     *            the id
     * @return the Posts for the version 1
     */
    private Response getPostsV1(Integer id) {
        Post iPost = PostHome.findById(id, null);
        if (iPost == null) {
            _logger.error(Constants.ERROR_NOT_FOUND_RESOURCE);
            return Response.status(Response.Status.NOT_FOUND).entity(JsonUtil.buildJsonResponse(
                    new ErrorJsonResponse(Response.Status.NOT_FOUND.name(), Constants.ERROR_NOT_FOUND_RESOURCE)))
                    .build();
        }

        return Response.status(Response.Status.OK).entity(JsonUtil.buildJsonResponse(new JsonResponse(iPost))).build();
    }
}