<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@ include file="/WEB-INF/view/localizationGeneral.jsp" --%> 

<div class="body-title">
	<h2> ${registration_page} Registration page</h2>
	<h3> ${registration_text}  To fill in the empty fields, please:</h3>
</div>

<div class="add-table-margin">
	<form:form action="doRegistration" modelAttribute="user" method="POST">
	
	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text">${name} Enter name:   </td>
			<td class="space_around_view_text">
			<div class="word-breaker">
			<form:input id="userName" path="userDetails.userName"/>
			</div></td>
			
		</tr>
		
		<tr>
			<td class="space_around_title_text">${surname} Enter surname:   </td>
			<td class="space_around_view_text">
			<div class="word-breaker">
			<form:input id="userSurname" path="userDetails.userSurname"/>
				
			</div></td>
			
		</tr>
		
		<tr>
			<td class="space_around_title_text">${birthday}  Enter birthday:  </td>
			<td class="space_around_view_text">
			<div class="word-breaker"> 
			<form:input id="birthday" path="userDetails.birthday"  />
				
			</div></td>
			
		</tr>
		
		<tr>
			<td class="space_around_title_text">${email} Enter email:</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
			<form:input id="email" path="userDetails.email" />
				
			</div></td>
				
		</tr>
		<tr>
			<td class="space_around_title_text">${login} Enter login:</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
			<form:input id="login" path="login"/>
				
			</div></td>
			
		</tr>
		<tr>
			<td class="space_around_title_text">${password} Enter password:</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
			<form:input id="password" path="password"/>
				
			</div></td>
			
		</tr>
    	</table>
	
	
	<div class="first-view-button">
		<input type="submit" value="Registration" />
		<!--  <input type="hidden" name="command" value="do_registration" /> 
		<input type="submit" value="${registration_button}" />-->
	</div>

</form:form> 
	
	<div class="second-view-button">
	<form action="doSignOut" method="post">
	<input type="submit" value="Cancel" />
	</form>
	
	<!--  <form action="controller" method="post">
		<input type="hidden" name="command" value="do_sign_out" /> 
		<input type="submit" value="${cancel}" />-->
	
 </div>   

</div>






