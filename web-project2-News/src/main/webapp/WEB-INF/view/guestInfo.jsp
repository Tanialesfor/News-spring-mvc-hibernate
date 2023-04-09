<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="body-title">
	<a href=""> ${news_} News </a> ${latest_news} Latest news
	<br /> <br /> <br /> <br />
</div>

<form action="controller" method="post">
	
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
			</div>
		</div>

	</c:forEach>

	<div class="no-news">
		<c:if test="${news eq null}">
        ${no_news}
	</c:if>
	</div>

</form>
</body>
</html>