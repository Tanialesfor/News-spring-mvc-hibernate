<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--@ include file="/WEB-INF/view/localizationGeneral.jsp" --%>

<div class="body-title">
	<a href="goToNewsList"> ${news_}News>> </a> ${edit_news_b}Edit News>>
	<br /> <br /> <br /> <br />
</div>

<div class="add-table-margin">
<form action="doAddNews" method="post">
	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text">${news_title} Title</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
				<input type="text" name="title" value="${news.title }" > 
			</div></td>	
			
		</tr>
		<tr>
			<td class="space_around_title_text">${news_date} News Date</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
				<input type="text" name="date" value="${news.newsDate }">
			</div></td>
				
		</tr>
		<tr>
			<td class="space_around_title_text">${brief} Brief</td>
			<td class="space_around_view_text">
			<div class="word-area-breaker">
			<textarea rows="8" cols="50" name="brief"> <c:out value="${news.brief }" />
			</textarea>
			</div></td>
			
		</tr>
		<tr>
			<td class="space_around_title_text">${content} Content</td>
			<td class="space_around_view_text">
			<div class="word-area-breaker">
			<textarea rows="12" cols="50" name="content" > <c:out value="${news.content }" />
			</textarea>
			</div></td>
			
		</tr>
	</table>
	
	<c:if test="${role eq 'admin' || role eq 'editor'}">
	<div class="first-view-button">
			<input type="hidden" name="command" value="do_edit_news" /> 
			<input type="hidden" name="id" value="${news.idNews}" /> 
			<input type="submit" value="Save" />
			<!--  <input type="submit" value="${save}" />-->
		</div>
	</c:if>	
</form>
	
	<div class="second-view-button">
		<form action="goToViewNews" method="post">
			<input type="hidden" name="command" value="go_to_view_news" /> 
			<input type="hidden" name="id" value="${news.idNews}" /> 
			<input type="submit" value="Cancel" />
			<!--  <input type="submit" value="${cancel}" />-->
		</form>
	</div>
	
</div>