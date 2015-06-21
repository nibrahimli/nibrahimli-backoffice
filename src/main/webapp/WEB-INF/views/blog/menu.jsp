<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">Blog</div>
	<div class="list-group">
	  <a class="list-group-item" href="<c:url value="/blog/articles" />">Articles</a>
	  <a class="list-group-item" href="<c:url value="/blog/article" />">New article</a>
	  <a class="list-group-item" href="<c:url value="/blog/authors" />">Authors</a>
	  <a class="list-group-item" href="<c:url value="/blog/author" />">New author</a>
  </div>
</div>

<style type="text/css">
div.panel {
	margin-top: 35px;	
}
</style>