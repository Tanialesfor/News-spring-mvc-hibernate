<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--@ include file="/WEB-INF/view/localizationGeneral.jsp" --%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


</head>
<body>
<h1>${error_page} Error Page </h1>
<h3> ${error_text} Sorry. Your request could not be fulfilled.</h3>
<br /> <br /> 
<h4> ${error_message} Message of error:</h4> 

<c:if test="${not (errorMessage eq null)}">
		<font color="red" > 
			<c:out value="${errorMessage}" />
		</font> 
		<br /> <br /> 
</c:if>

<form action="goToBasePage" method="post">
		
		<input type="submit" value="Back" />
		<!--  <input type="submit" value="${back}" />-->
	</form>

</body>
</html>