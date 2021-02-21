<jsp:useBean id="managePosts" scope="session" class="fr.paris.lutece.plugins.rokmeul.web.PostsJspBean" />
<% String strContent = managePosts.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
