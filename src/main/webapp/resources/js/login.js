$(document).ready(function(){
$('#Login').click(function(){
	var username = $("#username").val();
	var password = $("#password").val();
	var user = {"username":username, "password":password};
	
	$.ajax({
		url:"http://localhost:8080/twitterrest/login",
		type:"POST",
		dataType:"JSON",
		contentType:"application/json",
		async:true,
		data:JSON.stringify(user),
		success:function(data){
			if(data.code==200){
				window.location.replace("http://localhost:8080/twitterrest/home");
			}
			else{
				$("#error").text(data.message);
			}
		},
		failure:function(data){
		}
	});
});
});