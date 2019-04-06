<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Administration</title>
</head>
<body>
	<div>
		<h3>Login</h3>
		<c:if test="${not empty error }">
			<p style="color:red;">${error }</p>
		</c:if>
		<c:if test="${not empty msg }">
			<p style="color:red;">${msg }</p>
		</c:if>
		
		<form method="post" action="<c:url value='/login'/>">
			<label for="userName">Nom d'utilisateur</label>
			<input type="text" name="userName" id="userName" placeholder="Votre nom d'utilisateur"/>
			<label for="password">Mot de passe</label>
			<input type="password" name="password" id="password" placeholder="Votre mot de passe" />
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
			<button>Login</button>
		</form>
		
	</div>
</body>
</html>