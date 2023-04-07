<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--@ include file="/WEB-INF/view/localizationGeneral.jsp" --%> 

<div class="body-title">
	<a href="goToNewsList">${news_} News>> </a> ${news_list_b} News list>>
	<br /> <br /> <br /> <br />
	</div>

			
<form:form action="doDeleteNews" modelAttribute="news" method="POST">
<form:hidden path="idNews" />
<!--<form action="controller" method="post">-->
		
	<c:forEach var="tempNews" items="${newsList}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
					<!--<c:out value="${news.title}" />-->
					<c:out value="${tempNews.title}" />
				</div>
				<div class="news-date">
					<c:out value="${tempNews.newsDate}" />
					
				</div>

				<div class="news-content">
					<c:out value="${tempNews.brief}" />
				</div>
				<div class="news-link-to-wrapper">
					<div class="link-position">
						
						
						
						<c:if test="${sessionScope.role eq 'admin' || sessionScope.role eq 'editor'}">
						     <!--   <a href="controller?command=go_to_edit_news&id=${news.idNews}"> ${editlink} </a> -->
						      <c:url var="editLink" value="/controller/goToEditNews">
						      <c:param name="idNews" value="${tempNews.idNews}" />
					           </c:url>
						      <a href="${editLink}"> Edit </a>
						</c:if>
						
						<c:url var="viewLink" value="/controller/goToViewNews">
						      <c:param name="idNews" value="${tempNews.idNews}" />
					          </c:url>
					          <a href="${viewLink}"> View </a>
						<!--  <a href="controller?command=go_to_view_news&id=${news.idNews}"> ${viewlink} </a> -->
   					    
   					    <c:if test="${role eq 'admin' || role eq 'editor'}">
   					         <input type="checkbox" name="id" value="${tempNews.idNews}" />
   					    </c:if>
					</div>
				</div>

			</div>
		</div>

	</c:forEach>
	 
	 	
	<!-- 	<logic:notEmpty name="newsForm" property="newsList">
		<div class="delete-button-position">
			<html:submit>
				<bean:message key="locale.newslink.deletebutton" />
			</html:submit>
		</div>
	</logic:notEmpty> -->

	<div class="no-news">
		<c:if test="${newsList eq null}">
        ${no_news}
	</c:if>
	</div>

	<c:if test="${role eq 'admin' || role eq 'editor'}">
		<div class="first-view-button">
		<input type="submit" value="Delete" />
			<!--  <input type="hidden" name="command" value="do_delete_news" />--> 
			<!--  <input type="submit" value="${delete}" />-->
					
			
	</div>
	</c:if>	
	</form:form>
<!--</form>-->