<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="col-md-2"">
	<c:import url="/WEB-INF/views/blog/menu.jsp"/>  
</div>

<div class="col-md-10">
	<fieldset>
			<legend>Edit text</legend>
			<div class="col-md-10">
				
				<c:import url="/WEB-INF/views/tags/status.jsp" />
				
				<c:url var="url" value="/blog/article" />
				<form:form action="${url}" class="form-horizontal" commandName="articleInfo" method="POST">
				   <form:hidden path="id"/>
				   <div class="form-group">
				    <form:label class="col-sm-2 control-label" path="authorList">Authors</form:label>
				    <div class="col-sm-2">
				      <form:select class="form-control" path="authorList" multiple="true">
				      	 <form:options items="${authorList}" itemValue="id" itemLabel="firstName"/>
				      </form:select>
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <form:label class="col-sm-2 control-label" path="date">Date</form:label>
				    <div class="col-sm-2">
				      <form:input class="form-control" path="date"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <form:label class="col-sm-2 control-label" path="keywords">Keywords</form:label>
				    <div class="col-sm-10">
				      <form:input class="form-control" path="keywords" placeholder="Keywords"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <form:label class="col-sm-2 control-label" path="title">Title</form:label>
				    <div class="col-sm-10">
				      <form:input class="form-control" path="title" placeholder="Title"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <form:label class="col-sm-2 control-label" path="text">Text</form:label>
				    <div class="col-sm-10">
				      <form:textarea class="form-control" path="text" rows="20"></form:textarea>
				    </div>
				  </div>
				 
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="submit" class="btn btn-primary">Save</button>
				      <a href="<c:url value="/blog/articles"/>" class="btn btn-default">Cancel</a>
				    </div>
				  </div>
				</form:form>
				
			</div>	
	</fieldset>			
</div>

<script type="text/javascript">

$(function() {
	$( "#date" ).datepicker({
		dateFormat: "yy-mm-dd",
		changeMonth: true,
		numberOfMonths: 1
	});
});
</script>