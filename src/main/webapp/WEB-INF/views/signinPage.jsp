<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericPage>
   <jsp:attribute name="header">
   </jsp:attribute>
   <jsp:attribute name="footer">
   </jsp:attribute>
   <jsp:body>
   <div class="site-wrapper">
   		<div class="container">
   			<div class="col-md-6">
   			   	<%@ include file="/WEB-INF/views/forms/signin.jsp" %>
   			</div>
   		</div>
   </div>
   
   
   
   </jsp:body>
</t:genericPage>