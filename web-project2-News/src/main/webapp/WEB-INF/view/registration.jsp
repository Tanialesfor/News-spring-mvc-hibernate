<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
			     <td><form:input type="text" path="userName" placeholder="symbols from 1 to 10" /></td>
			</div></td>
			
		</tr>
		
		<tr>
			<td class="space_around_title_text">${surname} Enter surname:   </td>
			<td class="space_around_view_text">
			<div class="word-breaker">
				 <form:input type="text" path="userSurname" placeholder="symbols from 1 to 15" />
				
			</div></td>
			
		</tr>
		
		<tr>
			<td class="space_around_title_text">${birthday}  Enter birthday:  </td>
			<td class="space_around_view_text">
			<div class="word-breaker">
			     <form:input type="text" path="birthday" placeholder="template YYYY-MM-DD" />
				
			</div></td>
			
		</tr>
		
		<tr>
			<td class="space_around_title_text">${email} Enter email:</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
				<form:input type="text"  path="email" />
				
			</div></td>
				
		</tr>
		<tr>
			<td class="space_around_title_text">${login} Enter login:</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
				<form:input type="text" path="login" placeholder="symbols from 1 to 10"/>
				
			</div></td>
			
		</tr>
		<tr>
			<td class="space_around_title_text">${password} Enter password:</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
				<form:input type="text" path="password" />
				
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
	<input type="submit" value="Cancel" />
	<!--  <form action="controller" method="post">
		<input type="hidden" name="command" value="do_sign_out" /> 
		<input type="submit" value="${cancel}" />
	</form>-->
 </div>   

</div>






