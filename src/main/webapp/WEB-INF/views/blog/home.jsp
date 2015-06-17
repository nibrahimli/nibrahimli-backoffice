<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


	<div class="col-md-2"">
      	<c:import url="/WEB-INF/views/blog/menu.jsp"/>  
    </div>

	<div class="col-md-10"">
		<h1>Bootstrap starter template</h1>
        <p class="lead">Use this document as a way to quickly start any new project.<br> All you get is this text and a mostly barebones HTML document.</p>
		<c:forEach items="${userList}" var="user">
		   <p>user : ${user.firstName}</p>
		</c:forEach>
	
	
		<a href="test">Click here to test Exception handling</a>
		
	    <a href="?theme=default">def</a> 
	    | 
	    <a href="?theme=black">blk</a>
	    | 
	    <a href="?theme=blue">blu</a>
    </div> 
    
    
