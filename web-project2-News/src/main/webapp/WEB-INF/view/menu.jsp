<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--@ include file="/WEB-INF/view/localizationGeneral.jsp" --%> 

<div class="menu-wrapper">
	<div class="menu-title-wrapper">
		<div class="menu-title">
		       ${news}News
		</div>
	</div>

	<div class="list-menu-invisible-wrapper">
		<div class="list-menu-wrapper" style="float: right;">
			<ul style="list-style-image: url(images/img.jpg); text-align: left;">
				<li style="padding-left: 15px;">
				
				<a href="goToNewsList"> ${news_list} News List</a><br />
				</li>

				<c:if test="${role eq 'admin' || role eq 'editor'}">
				   <li style="padding-left: 15px;">
				
				    <a href="goToAddNews"> ${add_news} Add news</a>
                
                   <br />
					
				</li></c:if>
			</ul>
		</div>
		<div class="clear"></div>
	</div>
	<!--  grey free space at the bottom of menu -->
	<div style="height: 25px;"></div>
</div>

