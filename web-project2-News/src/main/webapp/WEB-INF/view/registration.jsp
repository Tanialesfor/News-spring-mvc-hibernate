<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@ include file="/WEB-INF/view/localizationGeneral.jsp" --%> 

<style>
.error {color: red}
</style> 

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
			<form:input id="userName" path="userDetails.userName"  placeholder="enter symbols from 1 to 10"/>
			<form:errors path="userDetails.userName" cssClass = "error"/>
			</div></td>
			
		</tr>
		
		<tr>
			<td class="space_around_title_text">${surname} Enter surname:   </td>
			<td class="space_around_view_text">
			<div class="word-breaker">
			<form:input id="userSurname" path="userDetails.userSurname" placeholder="enter symbols from 1 to 15"/>
			<form:errors path="userDetails.userSurname" cssClass = "error"/>
				
			</div></td>
			
		</tr>
		
		<tr>
			<td class="space_around_title_text">${birthday}  Enter birthday:  </td>
			<td class="space_around_view_text">
			<div class="word-breaker"> 
			<form:input id="birthday" path="userDetails.birthday" placeholder="template YYYY-MM-DD" />
			<form:errors path="userDetails.birthday" cssClass = "error"/>
			</div></td>
			
		</tr>
		
		<tr>
			<td class="space_around_title_text">${email} Enter email:</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
			<form:input id="email" path="userDetails.email" />
			<form:errors path="userDetails.email" cssClass = "error"/>
			</div></td>
				
		</tr>
		<tr>
			<td class="space_around_title_text">${login} Enter login:</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
			<form:input id="login" path="login" placeholder="enter symbols from 1 to 10"/>
			<form:errors path="login" cssClass = "error"/>
				
			</div></td>
			
		</tr>
		<tr>
			<td class="space_around_title_text">${password} Enter password:</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
			<form:input id="password" path="password" placeholder="enter symbols from 1 to 10"/>
			<form:errors path="password" cssClass = "error"/>
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






