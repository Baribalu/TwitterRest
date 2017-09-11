$(document).ready(function(){
	
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/twitterrest/friends/getFriends",
		dataType:"json"
	}).then(function(data){
		for(var index = 0; index < data.length; index++){
				var input="<button type=\"submit\" id=\"unfollow\" onclick=\"unfollow('"+data[index].username+"')\" value=\""+ data[index].username +"\">Unfollow</button>";
				$('#friends').append("<p>"+ data[index].username + " " + data[index].fullName + " " + data[index].age + " " + input +"</p>");
				$("#friends").append("<input style=\"display:none\" value=" + data[index].username + "\"/>");
		}
	});
	
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/twitterrest/friends/getUsers",
		dataType:"json"
	}).then(function(data){
		for(var index = 0; index < data.length; index++){
			var input="<button type=\"submit\" id=\"follow\" onclick=\"follow('"+data[index].username+"')\" value=\""+ data[index].username +"\">Follow</button>";
				$('#friends').append("<p>"+ data[index].username + " " + data[index].fullName + " " + data[index].age + " " + input +"</p>");
				$("#friends").append("<input style=\"display:none\" value=" + data[index].username + "\"/>");
		}
		
	});
	
	
	
});
function follow(data){
	var friend = {"username":  data, "age": 0};
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
	var friend = {"username":  data, "age": 0};
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