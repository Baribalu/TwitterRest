$(document).ready(function(){
	
	$.ajax({
		type : 'GET',
		url : 'http://localhost:8080/twitterrest/home/getMessages',
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
	var user = document.getElementById("user").value;
	var data = {"user":  user, "content" :  content};
	$.ajax({
		url: "http://localhost:8080/twitterrest/home/addMessage",
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
			$.ajax({
				url: 'http://localhost:8080/twitterrest/search/'+searchUser,
				type:'GET',
			    dataType : 'json'
			}).then(
					function(data) {	
						var input;
						if(data.isFriend){
							input="<input type=\"submit\" id=\"unfollow\" value=\"Unfollow\">";
						}else{
							input="<input type=\"submit\" id=\"follow\" value=\"Follow\">";
						}
							$('#userSearch').append("<p>"+ data.username + " " + data.fullName + " " + data.age + " " + input +"</p>");
					})
			});
});