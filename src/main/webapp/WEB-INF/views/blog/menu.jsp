<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="bs-docs-sidebar sideNavbar">
	<ul class="nav bs-docs-sidenav">		
		<li><a href="<c:url value="/blog/articles" />">Articles</a></li>
		<li><a href="<c:url value="/blog/article" />">New article</a></li>
		<li><a href="<c:url value="/blog/authors" />">Authors</a></li>
		<li><a href="<c:url value="/blog/author" />">New author</a></li>			
	</ul>
</nav>

<style type="text/css">
nav.bs-docs-sidebar {
	margin-top: 40px;
	margin-bottom : 40px;
}
nav.sideNavbar{
	background-color: ivory;
}
</style>