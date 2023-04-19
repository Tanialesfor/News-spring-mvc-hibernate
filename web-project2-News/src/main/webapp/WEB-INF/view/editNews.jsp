<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@ include file="/WEB-INF/view/localizationGeneral.jsp" --%>

<style>
.error {color: red}
</style>

<div class="body-title">
	<a href="goToNewsList"> ${news_}News>> </a> ${edit_news_b}Edit News>>
	<br /> <br /> <br /> <br />
</div>

<div class="add-table-margin">
<form:form action="doEditNews" modelAttribute="news" method="post">
<form:hidden path="idNews" />
	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text">${news_title} Title</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
				<form:input path="title"/> 
				 <form:errors path="title" cssClass = "error"/>
				 
			</div></td>	
			
		</tr>
		<tr>
			<td class="space_around_title_text">${news_date} News Date</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
				<form:input path="newsDate"/>
				<form:errors path="newsDate" cssClass = "error"/>
			</div></td>
				
		</tr>
		<tr>
			<td class="space_around_title_text">${brief} Brief</td>
			<td class="space_around_view_text">
			<div class="word-area-breaker">
			<form:textarea rows="8" cols="55" path="brief"/> <!--<c:out value="${news.brief }" />-->
			<form:errors path="brief" cssClass = "error"/>	
			</div></td>
			
		</tr>
		<tr>
			<td class="space_around_title_text">${content} Content</td>
			<td class="space_around_view_text">
			<div class="word-area-breaker">
			<form:textarea rows="12" cols="55" path="content" /> <!--<c:out value="${news.content }" />-->
			<form:errors path="content" cssClass = "error"/>
			</div></td>
			
		</tr>
	</table>
	
	<c:if test="${role eq 'admin' || role eq 'editor'}">
	<div class="first-view-button">
			 
			<input type="hidden" name="id" value="${news.idNews}" /> 
			<input type="submit" value="Save" />
			<!--  <input type="submit" value="${save}" />-->
		</div>
	</c:if>	
</form:form>
	
	<div class="second-view-button">
		<form action="goToViewNews" method="post">
			
			<input type="hidden" name="id" value="${news.idNews}" /> 
			<input type="submit" value="Cancel" />
			<!--  <input type="submit" value="${cancel}" />-->
		</form>
	</div>
	
</div>