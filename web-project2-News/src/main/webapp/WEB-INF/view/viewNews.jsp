<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--@ include file="/WEB-INF/view/localizationGeneral.jsp" --%>

<div class="body-title">
	<a href="goToNewsList"> ${news_} News>> </a> ${view_news_b}View News>>
	<br /> <br /> <br /> <br />
</div>

<div class="add-table-margin">
	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text"> ${news_title} Title</td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${news.title }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">${news_date}News Date</td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${news.newsDate }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"> ${brief} Brief</td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${news.brief}" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">${content} Content</td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${news.content }" />
				</div></td>
		</tr>
	</table>
</div>


<c:if test="${role eq 'admin'}">
<div class="first-view-button">
	<form action="goToEditNews" method="post">
		<input type="hidden" name="command" value="go_to_edit_news" /> 
		<input type="hidden" name="id" value="${news.idNews}" /> 
		<input type="submit" value="Edit" />
			<!--  <input type="submit" value="${edit}" />-->
	</form>
</div>

<div class="second-view-button">
	<form action="doDeleteNews" method="post">
		<input type="hidden" name="command" value="do_delete_news" /> 
		<input type="hidden" name="id" value="${news.idNews}" /> 
		<input type="submit" value="Delete" />
		<!--  <input type="submit" value="${delete}" />-->
	</form>
</div>
</c:if>