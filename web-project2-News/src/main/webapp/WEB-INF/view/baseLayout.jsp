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
<script type="text/javascript" src="script/validation.js"></script>
<title>locale.linkname.headertitle <!-- <bean:message key="locale.linkname.headertitle" />
 -->
</title>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/newsStyle.css"/>">

</head>
<body>

	<div class="page">
		<div class="header">
			<c:import url="/WEB-INF/view/header.jsp" />
		</div>
		<div class="base-layout-wrapper">
			<div class="menu">
				<c:if test="${not (userStatus eq 'active')}">
				     ${welcom}
				</c:if>				
				<c:if test="${userStatus eq 'active'}">
					<c:import url="/WEB-INF/view/menu.jsp" />
				</c:if>
			</div>
			<div class="content">               
                 <c:if test="${userStatus eq 'active'}">
					<c:import url="/WEB-INF/view/body.jsp" />
				 </c:if>            		
	            <c:if test="${not (userStatus eq 'active')}">										
					<c:if test="${(presentation eq 'registration')}">
						<c:import url="/WEB-INF/view/registration.jsp" />
					</c:if>				
					<c:if test="${not (presentation eq 'registration')}">
                       <c:if test="${not (presentation eq 'error')}">
                       <c:import url="/WEB-INF/view/guestInfo.jsp" />
					</c:if>	
					</c:if>
					<c:if test="${(presentation eq 'error')}">
						<c:import url="/WEB-INF/view/error.jsp" />
					</c:if>	
																		
				</c:if>										
			</div>
		</div>
		<div class="footer">
			<c:import url="/WEB-INF/view/footer.jsp" />
		</div>
	</div>
</body>
</html>