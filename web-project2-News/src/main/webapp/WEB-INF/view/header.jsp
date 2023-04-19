<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@ include file="/WEB-INF/view/localizationGeneral.jsp" --%>

<div class="wrapper">
	<div class="newstitle"> ${news_management} News management </div>

	<div class="local-link">

		<div align="right">
			<a href="controller?command=do_localization&local=en"> 
			<c:out value="English" /> 
				<!--<c:out value="${en_button}" /> -->
			</a> &nbsp;&nbsp; 
			<a	href="controller?command=do_localization&local=ru"> 
			<c:out value="Russian" /> 
				<!--<c:out value="${ru_button}" /> -->
			</a> <br /> <br />
		</div>
	
		<c:if test="${not (userStatus eq 'active')}">
			<div align="right">
				<form action="doSignIn"  method="post">
					${login} Enter login:
					<input type="text" name="login" value="" title="symbols from 1 to 10"/><br />
					
					${password} Enter password:
					<input type="password" name="password" value="" title="symbols from 1 to 10"/><br />
				
					<c:if test="${not (sessionScope.authenticationError eq null)}">
						<font color="red"> 
						<c:out value="${authenticationError}" />
						<c:remove var="authenticationError"/>
						  <!--   <c:out value="${signIn_auther_error_text}" />-->
						   </font> 
						 </c:if>
						
										  
				  <c:if test="${not (sessionScope.registrationError eq null)}">
						<font color="red"> 
						<c:out value="${registrationError}" />
						  <!--   <c:out value="${doRegistration_auther_inf_text}" />-->
						   <c:remove var="registrationError"/>	
						</font> 
					</c:if>	
					
					<c:if test="${not (sessionScope.registrationMessage eq null)}">
						<font color="orange"> 
						   	<c:out value="${registrationMessage}" />
						  <!--   	<c:out value="${doRegistration_auther_message_text}" />-->
						   	<c:remove var="registrationMessage"/>						   	
						</font> 
					</c:if>	
					
										
					<c:if test="${not (sessionScope.registrationMessageInf eq null)}">
						<font color="red"> 
						   	<c:out value="${registrationMessageInf}" />
						   <!-- 	<c:out value="${doRegistration_auther_inf_text}" /> -->
						   	<c:remove var="registrationMessageInf"/>						   	
						</font> 
					</c:if>	
					
					<c:if test="${not (sessionScope.autherMessage eq null)}">
						<font color="blue"> 
					   		<c:out value="${autherMessage}" />
					   		<c:remove var="autherMessage"/>
						</font> 
					</c:if>
																								
					<a href="goToRegistrationPage"> ${registration} Registration </a> 
					
					<input type="submit" value="Sign In" /><br />
					<!-- <input type="submit" value="${sign_In}" /><br /> -->
				</form>
			</div>
		</c:if>
		
							
		<c:if test="${userStatus eq 'active'}">
			<div align="right">
            	<form action="doSignOut" method="post">
	            	<c:if test="${not (sessionScope.autherMessage eq null)}">
						<font color="blue"> 
						   	<c:out value="${autherMessage}" />
						   	<c:remove var="autherMessage"/>						   	
						</font> 
					</c:if>	
						
						<input type="submit" value="Sign Out" /><br />																								
   				               <!--<input type="hidden" name="command" value="do_sign_out" />  -->
   							<!-- <input type="submit" value="${sign_Out}" /><br /> -->
				</form>					
			</div>
		</c:if>				
	</div>
</div>
