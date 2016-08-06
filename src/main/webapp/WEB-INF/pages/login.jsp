<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Restaurant Web Service</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/normalize.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
	<div class="login">
        <h2>Restaurant Web Service</h2>
        <form  action="login" id="frmLogin" method="POST">
	      <fieldset>
	        <input type="text" name="username" placeholder="your username" />
	      	<input type="password"  name="password" placeholder="Password" />
	      </fieldset>
	      <input type="submit" value="Log In" />
	      <div class="utilities">
	          <a href="#">Siem Reap Class</a>
	         <a href="#">SR-Group2 &rarr;</a>
	      </div>
      </form>
    </div>
    <script src="${pageContext.request.contextPath}/resources/scripts/jquery-2.1.4.min.js"></script>
    <script type="text/javascript">
        
        $(function() {
        	
        	
        	$("#frmLogin").submit(function(e){
       		
       		  e.preventDefault();
       			
       		  $.ajax({
  	            url: "${pageContext.request.contextPath}/login",
  	            type: "POST",
  	            data: $("#frmLogin").serialize(),
//   	            beforeSend: function (xhr) {
//   	                xhr.setRequestHeader("X-Ajax-call", "true");
//   	            },
  	            success: function(data) {
  	            	if(data == "User account is locked"){
  	            		alert(data);
  	            	}else if(data == "User is disabled"){
  	            		alert(data);
  	            	}else if(data == "Bad credentials"){
  	            		alert(data);
  	            	}else{
  	            		alert("Login Success");
  	            		location.href = "${pageContext.request.contextPath}/"+data;
  	            	}
  	            	
  	            },
  	         	error: function(data){
  	         		console.log(data);
  				}
  	        });
       			
       	});
        	
        	$.ajax({ 
			    url: "http://localhost:8080/api/", 
			    type: 'GET', 
			    beforeSend: function(xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.setRequestHeader("Authorization" , "Basic c2V5aGE6Z3JvdXAyMTIzNDU2");
                },
			    success: function(data) { 
					console.log(data);
				  
					
			    },
			    error:function(data,status,er) { 
			        console.log("error: "+data+" status: "+status+" er:"+er);
			    }
			});
        	
        
      });
    </script>
</body>
</html>