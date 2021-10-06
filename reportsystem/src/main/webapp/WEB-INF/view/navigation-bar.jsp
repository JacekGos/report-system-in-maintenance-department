<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
 
<!DOCTYPE html>
<html>
<head>

</head>
<body>

	<header
		class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" href="${pageContext.request.contextPath}/"> <i
			class="bi bi-house-door"></i> Strona główna
		</a>
		<div class="btn-group">
			<button type="button" class="btn btn-secondary dropdown-toggle"
				data-bs-toggle="dropdown" aria-expanded="false">
				<i class="bi bi-person"></i>
			</button>
			<ul class="dropdown-menu dropdown-menu-end dropdown-menu-dark">
				<li>
				
				<form:form action="${pageContext.request.contextPath}/user/showUserOptions"
					method="GET">

					<button class="dropdown-item" type="submit">
						<i class="bi bi-gear-fill"></i> Opcje
					</button></li>

				</form:form>
				
				<form:form action="${pageContext.request.contextPath}/logout"
					method="POST">

					<button class="dropdown-item" type="submit">
						<i class="bi bi-power"></i> Wyloguj
					</button>

				</form:form>

			</ul>
		</div>
	</header>

</body>
</html>