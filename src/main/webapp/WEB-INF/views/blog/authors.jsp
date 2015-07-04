<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="col-md-2">
	<c:import url="/WEB-INF/views/blog/menu.jsp"/>  
</div>

<div class="col-md-10"">
	<fieldset>
	<legend>List of author</legend>
		
		<c:import url="/WEB-INF/views/tags/status.jsp" />
		
		<% int counter = 1; %>
		<div class="table-responsive">
		<table class="table">
			<thead>
				<tr>
					<th>Number</th>
					<th>Id</th>
					<th>Avatar</th>
					<th>Pseudo</th>
					<th>First name</th>
					<th>Last name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${authorList}" var="author">
				<tr>
					<td class="col-md-1"><%=counter%></td>  
					<td class="col-md-1">${author.id}</td>
					<td class="col-md-1">
						<c:choose>
							<c:when test="${not empty author.avatar.path}">
								<img src="<c:url value="/resources/avatars/${author.pseudo}/${author.avatar.path}"/>" class="img-responsive img-rounded media-object" width="40px">
							</c:when>
							<c:otherwise>
								<img src="<c:url value="/resources/avatars/default-avatar.png"/>" class="img-responsive img-rounded media-object" width="40px">
							</c:otherwise>
						</c:choose>
					</td>
					<td class="col-md-1">${author.pseudo}</td>
					<td class="col-md-1">${author.firstName}</td>
					<td class="col-md-1">${author.lastName}</td>
					<td class="col-md-3">${author.email}</td>
					<td class="col-md-2">
						<a class='btn btn-info btn-sm' href="<c:url value="/blog/author?id=${author.id}"/>"><i class="icon-edit icon-white"></i> Edit</a>
						<a class="btn btn-danger btn-sm" href="<c:url value="/blog/author/update?id=${author.id}&action=delete"/>"><span class="icon-remove icon-white"></span> Del</a>
					</td>
					<% counter++; %>
				</tr>
				</c:forEach>
			</tbody>	
		</table>
		</div>
	</fieldset>
</div>


