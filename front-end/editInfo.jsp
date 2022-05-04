<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Group Info-Editing</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="style.css">
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css"> -->
<link href="http://fonts.cdnfonts.com/css/handjet-2" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com"> 
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin> 
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/7f10118ced.js" crossorigin="anonymous"></script>
<%
	Boolean isLogin = true;
	String groupTitle = "CSCI201";
	String memberListString = "Tommy Trojan, Last First";
	String description = "This project will be a web-based group project management system similar to those used in the software industry. Users can create groups and add group members to their projects. They can also use build-in to-do list and calendar to manage a group project.";
	ArrayList<String> userEmailList = new ArrayList<String>();
	userEmailList.add("trojan@usc.edu");
	userEmailList.add("test@test.com");
%>
<script>

function add(){
	var newdiv = document.createElement('div');
	newdiv.id = "emailInput"
    newdiv.innerHTML = "<input class = 'memberInput' type='text' name='memberEmail'><input type='button' id = 'minusBtn' value='-' onClick='removeInput(this);'>";
    document.getElementById('addMember').appendChild(newdiv);
    }

function removeInput(btn){
    btn.parentNode.remove();
}
</script>             
</head>
<body>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script> -->
	
	<div class="container-fluid">
    <div class="row flex-nowrap">
    	<!-- sidebar -->
        <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0" id = "sidebar">
            <div class="d-flex flex-column align-items-center pt-2 text-white min-vh-100" id="sidebarContent">
            	<div class="sidebar-header">
                <a href="index.jsp" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                    <span class="d-none d-sm-inline">PM SYSTEM</span>
                </a>
                </div>
                <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
                    <li class="nav-item">
                        <a href="groupInfo.jsp" class="nav-link align-middle px-0" id="selectedBtn">
                           <i class="fa-solid fa-circle-info"></i> <span class="ms-1 d-none d-sm-inline">Group Info</span>
                        </a>
                    </li>
                    <li>
                        <a href="calendar.jsp" class="nav-link px-0 align-middle">
                            <i class="fa-solid fa-calendar"></i> <span class="ms-1 d-none d-sm-inline">Calendar</span></a>
                    </li>
                    <li>
                        <a href="todoList.jsp" data-bs-toggle="collapse" class="nav-link px-0 align-middle ">
                            <i class="fa-solid fa-list"></i> <span class="ms-1 d-none d-sm-inline">To-do List</span></a>
                    </li>
                    <li>
                        <a href="index.jsp" class="nav-link px-0 align-middle">
                            <i class="fa-solid fa-right-from-bracket"></i> <span class="ms-1 d-none d-sm-inline">Logout</span> </a>
                    </li>
                </ul>
                
                <%
                if (isLogin) {
                	out.println("<div class=\"userInfo\"> <table id=\"user\">");
                	
                	out.println("<tr><th id=\"icon\"><i class=\"fa-solid fa-user\"></i></th></tr>");
                	out.println("<tr><th id=\"name\">Tommy Trojan</th></tr>");
                	out.println("<tr><th id = \"email\">trojan@usc.edu</th></tr>");
                	out.println("</table></div>");
                	
                }
                %>
                
            </div>
        </div>
        <!-- sidebar end -->
        
        <!-- content -->
        <div class="col py-3">
        
		<form id="groupForm" action="groupDispatcher" method="post"> <!-- submit title and description -->
            <div class = "division"> 
            	<p class = "title">Group Information - Editing</p>
            	<div class="container infoPart">
            		<div class="row">
            			<div class="col-2"><label class = "info">Title</label></div>
    					<div class="col-7"><input class = "groupInput" type="text" name="groupTitle" value = <%=groupTitle %>></div>
   		 				<div class="col"></div>
            		</div>
            		<div class="row">
            		<!-- get multiple input with the same name: https://stackoverflow.com/a/41508004 -->
            			<div class="col-2"><label class = "info">Member</label></div>
            			<div class="col-7" id = "addMember">
            				<%
            				for (int i = 0; i< userEmailList.size(); i++) {
            					out.println("<div id=\"emailInput\"><p>"
            							+ userEmailList.get(i) + "</p></div>");
            				}
            				%>
            			</div>
            			<div class="col"><input type="button" onclick="add()" id="addMemberBtn" value = "+"></div>
            		</div>
            		<div class="row">
            			<div class="col-2"><label class = "info">Description</label></div>
    					<div class="col-7"><textarea name="groupDescription" rows="10" cols="30" id="userText"><%=description %></textarea></div>
   		 				<div class="col"></div>
            		</div>
            	</div>
            	<div class="container twoButtons" align = "right">
            		<div class = "row row-cols-2">
            		<div class="col"></div>
            		<div class="col"><button id = "editBtn" type = "submit"> Save </button></div>
            		<div class="col"></div>
            		<div class="col"><button id = "quitBtn" type = "button" onclick = "location.href = 'index.jsp'"> Quit </button></div>
            		
            		</div>
            	
            	</div>
            	
            	
            </div>
            </form>
        </div>
        <!-- content end -->
        
      </div>
     </div>

</body>
</html>