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
	<h1>Welcome</h1>
	<div>
		Logged in as ${userName}
		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl }" method="POST">
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
			<button>Logout</button>
		</form>
	</div>
</body>
</html>