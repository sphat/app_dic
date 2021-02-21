<jsp:useBean id="manageCategory" scope="session" class="fr.paris.lutece.plugins.rokmeul.web.CategoryJspBean" />
<% String strContent = manageCategory.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
