<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--@ include file="/WEB-INF/view/localizationGeneral.jsp" --%> 

<div class="body-title">
	<a href="goToNewsList">${news_} News>> </a> ${news_list_b} News list>>
	<br /> <br /> <br /> <br />
	</div>

			
<form:form action="doDeleteNews" modelAttribute="news" method="POST">

		
	<c:forEach var="tempNews" items="${news}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
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
						
						
						
						<c:if test="${role eq 'admin' || role eq 'editor'}">
						    
						      <c:url var="editLink" value="/controller/goToEditNews">
						      <c:param name="id" value="${tempNews.idNews}" />
					           </c:url>
						      <a href="${editLink}"> Edit </a>
						</c:if>
						
						<c:url var="viewLink" value="/controller/goToViewNews">
						      <c:param name="id" value="${tempNews.idNews}" />
					          </c:url>
					          <a href="${viewLink}"> View </a>
						
   					    
   					    <c:if test="${role eq 'admin' || role eq 'editor'}">
   					         <input type="checkbox" name="idNews" value="${tempNews.idNews}" />
   					    </c:if> 
					</div>
				</div>

			</div>
		</div>

	</c:forEach>
	 	
	
	<div class="no-news">
		<c:if test="${news eq null}">
        ${no_news} No news
	</c:if>
	</div>

	<c:if test="${role eq 'admin' || role eq 'editor'}">
		<div class="first-view-button">
		<input type="submit" value="Delete" />
									
	</div>
	</c:if>	
	</form:form>
