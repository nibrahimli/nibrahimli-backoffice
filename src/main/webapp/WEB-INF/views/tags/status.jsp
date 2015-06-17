<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty infoMessage}">				
	<div class="alert alert-success">
			<a class="close" data-dismiss="alert" href="#">x</a><b>Success!</b> ${infoMessage}
	</div>
</c:if>
				
<c:if test="${not empty errorMessage}">				
	<div class="alert alert-danger">
		<a class="close" data-dismiss="alert" href="#">x</a><b>Error!</b> ${errorMessage}
	</div>
</c:if>

<c:if test="${not empty warningMessage}">				
	<div class="alert alert-warning">
		<a class="close" data-dismiss="alert" href="#">x</a><b>Warning!</b> ${warningMessage}
	</div>
</c:if>