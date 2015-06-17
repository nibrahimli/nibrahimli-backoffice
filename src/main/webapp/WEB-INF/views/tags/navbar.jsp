<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-default navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span>
					<span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">nibrahimli</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="/">Home</a></li>
				<li><a href="<c:url value="/blog/articles" />">Blog</a></li>
			</ul>			
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value='/j_spring_security_logout'/>"><i class="icon-off"></i> Logout</a></li>				
			</ul>
		</div>		
	</div>
</nav>