<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Home</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" type="text/css" href="index.css">
        <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
            rel="stylesheet">
        <script src="https://kit.fontawesome.com/7f10118ced.js" crossorigin="anonymous"></script>
        <% 
        Cookie[] cookies  = request.getCookies();
        String userName = "";
        Boolean isLogin = false;
        if (cookies != null) {
        	for (Cookie aCookie : cookies) {
            	String temp = aCookie.getName();
            	userName = aCookie.getValue();
            	if (temp.equals("userName")) {
            		userName.replace("+", " ");
            		isLogin = true;
            		break;
            	}
            }
        }
        %>
        
    </head>

    <body>
    <div class="header">
    	<div class = "nav">
    		<a href="index.jsp" class = "logo">SalEats!</a>
    		<p><%if (isLogin) {
    			out.println("Hi " + userName.replace("+", " ") + " !");
    		}
    			%></p>
    		
    		<ul>
    		<%if (isLogin) {
    			//out.println("<li><a href=\"LogoutDispatcher\" onclick=\"signOut();\">Logout</a></li>");
    			out.println("<li><form action = \"LogoutDispatcher\" method = \"post\">");
    			out.print("<input type = \"submit\" value = \"Logout\"");
    			if (request.getAttribute("isGoogleLogin") != null){
    				if ((Boolean)request.getAttribute("isGoogleLogin")) {
    					out.print("onclick=\"signOut();\"");
    				}
    				
    			}
    			out.print(">");
    			out.println("</form>");
    		}
    		else {
    			out.println("<li><a href=\"auth.jsp\">Login/Register</a></li>");
    		}
    			%>
    			
    			<li><a href="index.jsp">Home</a></li>
    		</ul>
    	</div>
    </div>
<script>
  function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
    var redirectUrl = 'LogoutDispatcher';
    var form =  $('<form action="' + redirectUrl + '" method="post">' + '<input type="text" name="isGoogleLogout" value="1" />' +
    '</form>');
    $('body').append(form);
    form.submit();
  }
</script>
    <img src="image/banner.jpeg" class = "round-corner" alt ="banner">
    
    <form action="SearchDispatcher" method="GET">
    <div class="choosing">
		<select name="searchType" id="searchType" onchange="showChange()">
			
			<option value="nameSelected">Name</option>
			<option value="categorySelected">Category</option>
			
		</select>
		<input class = "search" type="text" name="userInput" >
		
		<button type="submit" class = "modifyButton"> <i class="fa-solid fa-magnifying-glass"></i> </button>
		<table>
			<tr>
				<td>
					<input type="radio" name="sort" value = "estimated_price" checked>
					<label for="price">Price</label> 
				</td>
				<td>
					<input type="radio" name="sort" value = "review_count">
					<label for="reviewCount">Review Count</label>
				</td>
			</tr>
			<tr>
				<td>
					<input type="radio" name="sort" value = "rating">
					<label for="rating">Rating</label> 
				</td>
			</tr>
		</table>
    </div>
    </form>
    <script type="module">
	import { initializeApp } from "https://www.gstatic.com/firebasejs/9.6.11/firebase-app.js";
 
  https://firebase.google.com/docs/web/setup#available-libraries

 
  const firebaseConfig = {
    apiKey: "AIzaSyC4wSi9WL1JXyt1ruw0WoD-5YBH99NaZlo",
    authDomain: "csci201-final.firebaseapp.com",
    projectId: "csci201-final",
    storageBucket: "csci201-final.appspot.com",
    messagingSenderId: "587400713595",
    appId: "1:587400713595:web:68f1ab01902cdd3abf59de"
  };

  // Initialize Firebase
  const app = initializeApp(firebaseConfig);
</script>
 </body>

 </html>