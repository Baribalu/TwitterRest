<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<html>
<head>
<title>Twitter</title>
<style>
body{
	text-align:center;
}
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
}

li {
	float: left;
}

li a {
	display: inline-block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li a:hover {
	background-color: #111;
}
</style>
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