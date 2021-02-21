/// *
// * Copyright (c) 2002-2020, Mairie de Paris
// * All rights reserved.
// *
// * Redistribution and use in source and binary forms, with or without
// * modification, are permitted provided that the following conditions
// * are met:
// *
// * 1. Redistributions of source code must retain the above copyright notice
// * and the following disclaimer.
// *
// * 2. Redistributions in binary form must reproduce the above copyright notice
// * and the following disclaimer in the documentation and/or other materials
// * provided with the distribution.
// *
// * 3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
// * contributors may be used to endorse or promote products derived from
// * this software without specific prior written permission.
// *
// * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
// * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
// * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
// * SUBSTITUTE GOODS OR SERVICES LOSS OF USE, DATA, OR PROFITS OR BUSINESS
// * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
// * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
// * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
// * POSSIBILITY OF SUCH DAMAGE.
// *
// * License 1.0
// */
//
// package fr.paris.lutece.plugins.rokmeul.web;
//
// import java.sql.Date;
// import java.util.List;
//
// import org.springframework.mock.web.MockHttpServletRequest;
// import org.springframework.mock.web.MockHttpServletResponse;
// import org.springframework.mock.web.MockServletConfig;
//
// import fr.paris.lutece.plugins.rokmeul.business.PostsHome;
// import fr.paris.lutece.portal.service.admin.AccessDeniedException;
// import fr.paris.lutece.portal.service.message.SiteMessageException;
// import fr.paris.lutece.portal.service.security.SecurityTokenService;
// import fr.paris.lutece.portal.web.LocalVariables;
// import fr.paris.lutece.portal.web.l10n.LocaleService;
// import fr.paris.lutece.test.LuteceTestCase;
// import fr.paris.lutece.util.date.DateUtil;
//
/// **
// * This is the business class test for the object Posts
// */
// public class PostsXPageTest extends LuteceTestCase {
// private static final String TITLE1 = "Title1";
// private static final String TITLE2 = "Title2";
// private static final String TEXT1 = "Text1";
// private static final String TEXT2 = "Text2";
// private static final Date CREATEDATE1 = new Date(1000000l);
// private static final Date CREATEDATE2 = new Date(2000000l);
// private static final Date UPDATEDATE1 = new Date(1000000l);
// private static final Date UPDATEDATE2 = new Date(2000000l);
// private static final int CATEGORYID1 = 1;
// private static final int CATEGORYID2 = 2;
// private static final int USERID1 = 1;
// private static final int USERID2 = 2;
// private static final boolean PUBLISH1 = true;
// private static final boolean PUBLISH2 = false;
// private static final String TEXTHTML1 = "TextHtml1";
// private static final String TEXTHTML2 = "TextHtml2";
//
// public void testXPage() throws AccessDeniedException {
// // Xpage create test
// MockHttpServletRequest request = new MockHttpServletRequest();
// MockHttpServletResponse response = new MockHttpServletResponse();
// MockServletConfig config = new MockServletConfig();
//
// PostsXPage xpage = new PostsXPage();
// assertNotNull(xpage.getCreatePosts(request));
//
// request = new MockHttpServletRequest();
// request.addParameter("token",
/// SecurityTokenService.getInstance().getToken(request, "createPosts"));
//
// LocalVariables.setLocal(config, request, response);
//
// request.addParameter("action", "createPosts");
// request.addParameter("title", TITLE1);
// request.addParameter("text", TEXT1);
// request.addParameter("create_date", DateUtil.getDateString(CREATEDATE1,
/// LocaleService.getDefault()));
// request.addParameter("update_date", DateUtil.getDateString(UPDATEDATE1,
/// LocaleService.getDefault()));
// request.addParameter("category_id", String.valueOf(CATEGORYID1));
// request.addParameter("user_id", String.valueOf(USERID1));
// request.addParameter("publish", String.valueOf(PUBLISH1));
// request.addParameter("text_html", TEXTHTML1);
// request.setMethod("POST");
//
// assertNotNull(xpage.doCreatePosts(request));
//
// // modify Posts
// List<Integer> listIds = PostsHome.getIdPostssList();
//
// assertTrue(!listIds.isEmpty());
//
// request = new MockHttpServletRequest();
// request.addParameter("id", String.valueOf(listIds.get(0)));
//
// assertNotNull(xpage.getModifyPosts(request));
//
// response = new MockHttpServletResponse();
// request = new MockHttpServletRequest();
// LocalVariables.setLocal(config, request, response);
// request.addParameter("title", TITLE2);
// request.addParameter("text", TEXT2);
// request.addParameter("create_date", DateUtil.getDateString(CREATEDATE2,
/// LocaleService.getDefault()));
// request.addParameter("update_date", DateUtil.getDateString(UPDATEDATE2,
/// LocaleService.getDefault()));
// request.addParameter("category_id", String.valueOf(CATEGORYID2));
// request.addParameter("user_id", String.valueOf(USERID2));
// request.addParameter("publish", String.valueOf(PUBLISH2));
// request.addParameter("text_html", TEXTHTML2);
//
// request.addParameter("token",
/// SecurityTokenService.getInstance().getToken(request, "modifyPosts"));
// request.addParameter("id", String.valueOf(listIds.get(0)));
// assertNotNull(xpage.doModifyPosts(request));
//
// // do confirm remove Posts
// request = new MockHttpServletRequest();
// request.addParameter("action", "confirmRemovePosts");
// request.addParameter("id", String.valueOf(listIds.get(0)));
// request.addParameter("token",
/// SecurityTokenService.getInstance().getToken(request, "confirmRemovePosts"));
// ;
// request.setMethod("GET");
//
// try {
// xpage.getConfirmRemovePosts(request);
// } catch (Exception e) {
// assertTrue(e instanceof SiteMessageException);
// }
//
// // do remove Posts
// response = new MockHttpServletResponse();
// request = new MockHttpServletRequest();
// LocalVariables.setLocal(config, request, response);
// request.addParameter("action", "removePosts");
// request.addParameter("token",
/// SecurityTokenService.getInstance().getToken(request, "removePosts"));
// request.addParameter("id", String.valueOf(listIds.get(0)));
// assertNotNull(xpage.doRemovePosts(request));
//
// }
//
// }