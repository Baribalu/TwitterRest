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
							input="<button type=\"submit\" id=\"unfollow\" onclick=\"unfollow('"+data.username+"')\" value=\""+ data.username +"\">Unfollow</button>";
						}else{
							input="<button type=\"submit\" id=\"follow\" onclick=\"follow('"+data.username+"')\" value=\""+ data.username +"\">Follow</button>";
						}
							$('#userSearch').append("<p>"+ data.username + " " + data.fullName + " " + data.age + " " + input +"</p>");
					})
			});
});

function follow(data){
	var friend = {"username":  data, "fullName" :  "", "age": "", "isFriend":""};
	$.ajax({
		url: "http://localhost:8080/twitterrest/friends/add",
		type:"post",
	        dataType : "json",
	        async : true,
	        data: JSON.stringify(friend),
	        contentType: "application/json; charset=utf-8",
		success: function(data) 
			{
			location.reload();

			},
			failure: function(data){
			}
		}); 
}

function unfollow(data){
	var friend = {"username":  data, "fullName" :  "", "age": "", "isFriend":""};
	$.ajax({
		url: "http://localhost:8080/twitterrest/friends/remove",
		type:"post",
	        dataType : "json",
	        async : true,
	        data: JSON.stringify(friend),
	        contentType: "application/json; charset=utf-8",
		success: function(data) 
			{
			location.reload();

			},
			failure: function(data){
			}
		}); 
}