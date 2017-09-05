<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<html>
<head>
<title>Twitter</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/menu.css"/>" />
</head>
<body>
	<h1>Twitter</h1>

	
	<ul>
		<li><a href="home">Home</a></li>
		<c:if test="${empty sessionScope.username}">
			<li><a href="login">Login</a></li>
		</c:if>
		<c:if test="${not empty sessionScope.username}">
			<li><a href="logout">Logout</a></li>
			<li><a href="friends">Friends</a></li>
		</c:if>
		<li><a href="about">About</a></li>
	</ul>
	<br/><br/><br/>
	<div>Goodbye ${sessionScope.username}!</div>
	<br/><br/>
	<form action="logout" method="POST">
		<div>
			<input type="submit" value="Logout"/>
		</div>
	</form>
	
</body>
</html>