<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>
<html>
<head>
<title>Twitter</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="<c:url value="/resources/js/home.js"/>">
</script>
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

	<h2>Hi ${sessionScope.username }!</h2>

	<div>${about}</div>


	<%
		if (session.getAttribute("username") != null) {
	%>
	<input type="text" id="content" />
	<input type="submit" value="Post" id="submit" />

	<input type="text" id="search" />
	<input type="submit" value="Search" id="submitSearch" />

	<div id="userSearch"></div>

	<div id="messages"></div>
	<%
		}
	%>
	
	
	<input id="user" style="display:none" value="<%=session.getAttribute("username")%>" >
</body>
</html>
