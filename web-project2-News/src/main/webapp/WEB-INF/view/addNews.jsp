<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--@ include file="/WEB-INF/view/localizationGeneral.jsp" --%> 

<div class="body-title">
	<a href="goToNewsList"> ${news_} News>> </a> ${add_news_b} Add News>>
	
</div>

<div class="add-table-margin">
<!--  <form action="controller" method="post">-->
<form:form action="doAddNews" modelAttribute="news" method="POST">

						
	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text">${news_title} Enter title</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
				 
			    <form:input path="title" />
			</div></td>
			
		</tr>
		<tr>
			<td class="space_around_title_text">${news_date} Enter date</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
				 <form:input path="newsDate" placeholder="template YYYY-MM-DD"/>
				
			</div></td>
				
		</tr>
		<tr>
			<td class="space_around_title_text">${brief} Enter brief</td>
			<td class="space_around_view_text">
			<div class="word-area-breaker">
			
					
			<form:textarea path = "brief" rows="8" cols="50" title="enter brief of news" />
						
			</div></td>
			
		</tr>
		<tr>
			<td class="space_around_title_text">${content} Enter content</td>
			<td class="space_around_view_text">
			<div class="word-area-breaker">
			
			<form:textarea path = "content" rows="12" cols="50" title="enter content of news" /> 
						
			</div></td>
			
		</tr>
	</table>
	<c:if test="${role eq 'admin' || role eq 'editor'}">
	<div class="first-view-button">
			<input type="hidden" name="command" value="do_add_news" /> 
			<input type="hidden" name="id" value="${news.idNews}" /> 
			 <input type="submit" value="Save" />
			<!--  <input type="submit" value="${save}" />-->
		</div>
	</c:if>
	</form:form>
	
	<div class="second-view-button">
	   <form action="goToNewsList">
	   <input type="hidden" name="id" value="${news.idNews}" />
	   <input type="submit" value="Cancel" />
	   </form>
	<!--<form action="controller" method="post">
			<input type="hidden" name="command" value="go_to_news_list" /> 
			<input type="hidden" name="id" value="${requestScope.news.idNews}" /> 
			<input type="submit" value="${cancel}" />
		</form>-->
	</div>
	
</div>


