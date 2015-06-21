<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="col-md-2">
	<c:import url="/WEB-INF/views/blog/menu.jsp"/>  
</div>

<div class="col-md-10">
	<fieldset>
			<legend>Edit author</legend>
			<div class="col-md-10">
				
				<c:import url="/WEB-INF/views/tags/status.jsp" />
			
				<c:url var="url" value="/blog/author" />
				<form:form action="${url}" class="form-horizontal" commandName="authorInfo" method="POST" enctype="multipart/form-data">
				   <form:hidden path="id"/>
				   <div class="form-group">
				    <label class="col-sm-2 control-label" for="file">Avatar</label>
				    <div class="col-sm-4">				    
				    <c:choose>
				    	<c:when test="${not empty authorInfo.avatar}">				    		
					    	<img src="<c:url value="/resources/avatars/${authorInfo.avatar.path}"/>" class="img-responsive img-rounded media-object" width="30px">
				    	</c:when>
				    	<c:otherwise>
				    		<img src="<c:url value="/resources/avatars/default-avatar.png"/>" class="img-responsive img-rounded media-object" width="40px">
				    	</c:otherwise>
				    </c:choose>								    
				      <input type="file" class="form-control" id="file" name="file"/>				      
				    </div>
				  </div>				   
				   <div class="form-group">
				    <form:label class="col-sm-2 control-label" path="pseudo">Pseudo</form:label>
				    <div class="col-sm-4">
				      <form:input class="form-control" path="pseudo"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <form:label class="col-sm-2 control-label" path="firstName">First name</form:label>
				    <div class="col-sm-4">
				      <form:input class="form-control" path="firstName" placeholder="First name"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <form:label class="col-sm-2 control-label" path="lastName">Last name</form:label>
				    <div class="col-sm-4">
				      <form:input class="form-control" path="lastName" placeholder="Last name"/>
				    </div>
				  </div>
				   <div class="form-group">
				    <form:label class="col-sm-2 control-label" path="password">Password</form:label>
				    <div class="col-sm-4">
				      <form:password class="form-control" path="password" showPassword="true"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <form:label class="col-sm-2 control-label" path="confirmPassword">Confirm password</form:label>
				    <div class="col-sm-4">
				      <form:password class="form-control" path="confirmPassword" showPassword="true"/>
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <form:label class="col-sm-2 control-label" path="email">Email</form:label>
				    <div class="col-sm-4">
				      <form:input class="form-control" path="email" placeholder="Email"/>
				    </div>
				  </div>
				  
				   <div class="form-group">
				    <form:label class="col-sm-2 control-label" path="confirmEmail">Confirm email</form:label>
				    <div class="col-sm-4">
				      <form:input class="form-control" path="confirmEmail" placeholder="Confirm email"/>
				    </div>
				  </div>

				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="submit" class="btn btn-primary">Save</button>
				      <a href="<c:url value="/blog/authors"/>" class="btn btn-default">Cancel</a>
				    </div>
				  </div>
				</form:form>
			</div>	
	</fieldset>			
</div>