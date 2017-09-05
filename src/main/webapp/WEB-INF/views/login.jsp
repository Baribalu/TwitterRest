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
	<sf:form modelAttribute="account" action="login" method="POST">
		<div>
			<label>Username:</label>
			<sf:input path="username" type="text" />
		</div>
		<br/>
		<div>
			<label>Password:</label>
			<sf:input path="password" type="password" />
		</div>
		<br/>
		<div>
			<input type="submit" value="Login" />
		</div>
	</sf:form>
	<br/>
	<div>${error}</div>

</body>
</html>