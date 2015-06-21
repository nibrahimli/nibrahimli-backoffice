<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="col-md-11 col-md-offset-1">
	<fieldset>
		<legend>Login</legend>
		<form class="form-horizontal"
			action='<c:url value='/j_spring_security_check' />' method="POST">
			<c:if test="${not empty error}">
				<div class="alert alert-danger alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					Your login attempt was not successful, try again. Caused :
					${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
				</div>
			</c:if>


			<div class="form-group">
				<label for="username" class="col-sm-2 control-label"><strong>Username</strong></label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="username" name="j_username">
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label"><strong>Password</strong></label>
				<div class="col-sm-2">
					<input type="password" class="form-control" id="password" name="j_password">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Sign in</button>
				</div>
			</div>
		</form>
	</fieldset>
</div>
