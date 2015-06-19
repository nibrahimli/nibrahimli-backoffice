<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="col-md-2">
	<c:import url="/WEB-INF/views/blog/menu.jsp"/>  
</div>

<div class="col-md-10"">
	<fieldset>
	<legend>List of article</legend>
		
		<c:import url="/WEB-INF/views/tags/status.jsp" />
		
		<% int counter = 1; %>
		<div class="table-responsive">
		<table class="table">
			<thead>
				<tr>
					<th>Number</th>
					<th>Id</th>
					<th>Date</th>
					<th>Authors</th>
					<th>Keywords</th>
					<th>Title</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${articleList}" var="article">
					<tr>
					<td class="col-md-1"><%=counter%></td>  
					<td class="col-md-1">${article.id}</td>
					<td class="col-md-1"><fmt:formatDate value="${article.date}" type="both" pattern="yyyy-MM-dd" /></td>
					<td class="col-md-3">
					<c:forEach items="${article.authorList}" var="author" varStatus="autorStatus">
						<c:if test="${autorStatus.index} > 0">
							|
						</c:if>
							${author.firstName}
					</c:forEach>
					</td>
					<td class="col-md-2">${article.keywords}</td>
					<td class="col-md-2">${article.title}</td>
					<td class="col-md-2">
						<a class='btn btn-info btn-sm' href="<c:url value="/blog/article?id=${article.id}"/>"><i class="icon-edit icon-white"></i> Edit</a>
						<a class="btn btn-danger btn-sm" href="<c:url value="/blog/article/update?id=${article.id}&action=delete"/>"><span class="icon-remove icon-white"></span> Del</a>
					</td>
					<% counter++; %>
					</tr>
				</c:forEach>
			</tbody>	
		</table>
		</div>
	</fieldset>
</div>

