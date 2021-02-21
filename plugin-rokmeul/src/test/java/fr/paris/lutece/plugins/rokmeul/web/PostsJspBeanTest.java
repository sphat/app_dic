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
// import fr.paris.lutece.portal.business.user.AdminUser;
// import fr.paris.lutece.portal.service.admin.AccessDeniedException;
// import fr.paris.lutece.portal.service.admin.AdminAuthenticationService;
// import fr.paris.lutece.portal.service.security.SecurityTokenService;
// import fr.paris.lutece.portal.service.security.UserNotSignedException;
// import fr.paris.lutece.portal.web.l10n.LocaleService;
// import fr.paris.lutece.test.LuteceTestCase;
// import fr.paris.lutece.util.date.DateUtil;
//
/// **
// * This is the business class test for the object Posts
// */
// public class PostsJspBeanTest extends LuteceTestCase {
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
// public void testJspBeans() throws AccessDeniedException {
// MockHttpServletRequest request = new MockHttpServletRequest();
// MockHttpServletResponse response = new MockHttpServletResponse();
// MockServletConfig config = new MockServletConfig();
//
// // display admin Posts management JSP
// PostsJspBean jspbean = new PostsJspBean();
// String html = jspbean.getManagePostss(request);
// assertNotNull(html);
//
// // display admin Posts creation JSP
// html = jspbean.getCreatePosts(request);
// assertNotNull(html);
//
// // action create Posts
// request = new MockHttpServletRequest();
//
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
// request.addParameter("action", "createPosts");
// request.addParameter("token",
/// SecurityTokenService.getInstance().getToken(request, "createPosts"));
// request.setMethod("POST");
// response = new MockHttpServletResponse();
// AdminUser adminUser = new AdminUser();
// adminUser.setAccessCode("admin");
//
// try {
// AdminAuthenticationService.getInstance().registerUser(request, adminUser);
// html = jspbean.processController(request, response);
//
// // MockResponse object does not redirect, result is always null
// assertNull(html);
// } catch (AccessDeniedException e) {
// fail("access denied");
// } catch (UserNotSignedException e) {
// fail("user not signed in");
// }
//
// // display modify Posts JSP
// request = new MockHttpServletRequest();
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
// List<Integer> listIds = PostsHome.getIdPostssList();
// assertTrue(!listIds.isEmpty());
// request.addParameter("id", String.valueOf(listIds.get(0)));
// jspbean = new PostsJspBean();
//
// assertNotNull(jspbean.getModifyPosts(request));
//
// // action modify Posts
// request = new MockHttpServletRequest();
// response = new MockHttpServletResponse();
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
// request.setRequestURI("jsp/admin/plugins/example/ManagePostss.jsp");
// // important pour que MVCController sache quelle action effectuer,
// // sinon, il redirigera vers createPosts, qui est l'action par défaut
// request.addParameter("action", "modifyPosts");
// request.addParameter("token",
/// SecurityTokenService.getInstance().getToken(request, "modifyPosts"));
// adminUser = new AdminUser();
// adminUser.setAccessCode("admin");
//
// try {
// AdminAuthenticationService.getInstance().registerUser(request, adminUser);
// html = jspbean.processController(request, response);
//
// // MockResponse object does not redirect, result is always null
// assertNull(html);
// } catch (AccessDeniedException e) {
// fail("access denied");
// } catch (UserNotSignedException e) {
// fail("user not signed in");
// }
//
// // get remove Posts
// request = new MockHttpServletRequest();
// // request.setRequestURI("jsp/admin/plugins/example/ManagePostss.jsp");
// request.addParameter("id", String.valueOf(listIds.get(0)));
// jspbean = new PostsJspBean();
// request.addParameter("action", "confirmRemovePosts");
// assertNotNull(jspbean.getModifyPosts(request));
//
// // do remove Posts
// request = new MockHttpServletRequest();
// response = new MockHttpServletResponse();
// request.setRequestURI("jsp/admin/plugins/example/ManagePoststs.jsp");
// // important pour que MVCController sache quelle action effectuer,
// // sinon, il redirigera vers createPosts, qui est l'action par défaut
// request.addParameter("action", "removePosts");
// request.addParameter("token",
/// SecurityTokenService.getInstance().getToken(request, "removePosts"));
// request.addParameter("id", String.valueOf(listIds.get(0)));
// request.setMethod("POST");
// adminUser = new AdminUser();
// adminUser.setAccessCode("admin");
//
// try {
// AdminAuthenticationService.getInstance().registerUser(request, adminUser);
// html = jspbean.processController(request, response);
//
// // MockResponse object does not redirect, result is always null
// assertNull(html);
// } catch (AccessDeniedException e) {
// fail("access denied");
// } catch (UserNotSignedException e) {
// fail("user not signed in");
// }
//
// }
// }
