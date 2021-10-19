<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
         
<!doctype html>
<html lang="en">

<head>
	
	<title>Login Page</title>
	
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>

	<div>
		<br>

		<div class="container">
			<br>
			<hr>

			<div class="row">
				<aside class="col-sm-4"></aside>
				<!-- col.// -->
				<aside class="col-sm-4">
					<div class="card">

						<article class="card-body">

							<h4 class="card-title text-center mb-4 mt-1">Zaloguj się</h4>
							<hr>
							<p class="text-success text-center">

								<c:if test="${param.error != null}">

									<div class="alert alert-danger col-xs-offset-1 col-xs-10">
										Zły login lub hasło.</div>

								</c:if>

								<c:if test="${param.logout != null}">

									<div class="alert alert-success col-xs-offset-1 col-xs-10">
										Zostałeś wylogowany.</div>

								</c:if>

							</p>

							<form:form
								action="${pageContext.request.contextPath}/authenticateTheUser"
								method="POST" class="form-horizontal">

								<div class="form-group">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text"> <i class="fa fa-user"></i>
											</span>
										</div>
										<input name="username" class="form-control"
											placeholder="Username" type="text">
									</div>
								</div>

								<div class="form-group">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text"> <i class="fa fa-lock"></i>
											</span>
										</div>
										<input name="password" class="form-control"
											placeholder="******" type="password">
									</div>

								</div>

								<div class="form-group">
									<button type="submit" class="btn btn-primary btn-block">
										Login</button>
								</div>
							</form:form>

						</article>

					</div>

				</aside>
				<aside class="col-sm-4"></aside>
			</div>
		</div>

	</div>




</body>
</html>