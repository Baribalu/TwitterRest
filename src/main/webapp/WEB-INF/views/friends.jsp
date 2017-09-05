<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>
<html>
<head>
<title>Twitter</title>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

table, th, td {
	border: 1px solid black;
}

th {
	height: 50px;
}
td{
	text-align:center;
}

body {
	text-align: center;
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

	<h2>${sessionScope.username}'sFriends!</h2>
	<br />

	<table>
		<tr>
			<th>Full Name</th>
			<th>Username</th>
			<th>Age</th>
			<th>Follow</th>
		</tr>
		<c:forEach items="${friends}" var="friend">
			<tr>
				<td>${friend.fullName}</td>
				<td>${friend.username}</td>
				<td>${friend.age}</td>

				<c:if test="${friend.isFriend}">
					<td><sf:form action="friends/remove"
							modelAttribute="newFriend">
							<sf:input type="text" style="display:none;" path="username"
								value="${friend.username }" />
							<input type="submit" value="Unfollow" />
						</sf:form></td>
				</c:if>
				<c:if test="${not friend.isFriend}">
					<td><sf:form action="friends/add" modelAttribute="newFriend">
							<sf:input type="text" style="display:none;" path="username"
								value="${friend.username }" />
							<input type="submit" value="Follow" />
						</sf:form></td>
				</c:if>

			</tr>
		</c:forEach>
	</table>

</body>


</html>