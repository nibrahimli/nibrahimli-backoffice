<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="bs-docs-sidebar hidden-print hidden-xs hidden-sm affix-top sideNavbar">
	<ul class="nav bs-docs-sidenav">
		<li class="nav-header"><strong>Blog</strong></li>
		<li><a href="<c:url value="/blog/articles" />">Articles</a></li>
		<li><a href="<c:url value="/blog/article" />">New article</a></li>
		<li><a href="<c:url value="/blog/authors" />">Authors</a></li>
		<li><a href="<c:url value="/blog/author" />">New author</a></li>			
	</ul>
</nav>

<style type="text/css">
nav.bs-docs-sidebar {
	margin-top: 40px;
}
nav.sideNavbar{
	background-color: ivory;
}
</style>