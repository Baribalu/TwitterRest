<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>
<html>
<head>
<title>Twitter</title>
<style>
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

</body>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
	$.ajax({
		type : 'GET',
		url : 'http://localhost:8091/twitter/getMessages',
		dataType : 'json'
	}).then(
			function(data) {
				for (var index = 0; index < data.length; index++) {
					$('#messages').append(
							"<p>" + data[index].user + ":"
									+ data[index].content + "</p>");
				}
			});
	 $('#submit').click( function() { 
	var content = document.getElementById("content").value;
	var user = "<%=session.getAttribute("username")%>";
	var data = {"user":  user, "content" :  content};
	$.ajax({
		url: "http://localhost:8091/twitter/addMessage",
		type:"post",
	        dataType : "json",
	        async : true,
	        data: JSON.stringify(data),
	        contentType: "application/json; charset=utf-8",
		success: function(data) 
			{
			location.reload();

			},
			failure: function(data){
			}
		}); 
	});
	 
	 
	 $('#submitSearch').click( function() { 
			var searchUser = document.getElementById("search").value;
			console.log(searchUser);
			$.ajax({
				url: 'http://localhost:8091/twitter/search/'+searchUser,
				type:'GET',
			    dataType : 'json',
			}).then(
					function(data) {	
							$('#userSearch').append("<p>"+ data.username + " " + data.fullName + " " + data.age +  "</p>");
					})
					});
</script>
</html>
