<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">

<head>

<title>Register New User Form</title>

<%@ include file="/WEB-INF/view/sources.jsp"%>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/form-validation.css" />

</head>

<body>

	<%@ include file="/WEB-INF/view/navigation-bar.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<%@ include file="/WEB-INF/view/side-bar-menu.jsp"%>

			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<h1 class="h2">Dodaj użytkownika</h1>

				</div>

				<div class="row">
					<aside class="col-sm-3"></aside>
					<aside class="col-sm-8">

						<div class="col-md-7 col-lg-8">
							<%-- 					<form class="needs-validation" novalidate> --%>
							<form:form class="row g-3"
								action="${pageContext.request.contextPath}/user/processUserForm"
								modelAttribute="userDto" accept-charset="UTF-8">

								<div class="row g-3">
									<div class="col-sm-6">
										<label class="form-label">Imię</label>
										<form:input path="firstName" placeholder="Imię"
											class="form-control" />
										<form:errors path="firstName" cssClass="error" />	
									</div>

									<div class="col-sm-6">
										<label class="form-label">Nazwisko</label>
										<form:input path="lastName" placeholder="Nazwisko"
											class="form-control" />
										<form:errors path="lastName" cssClass="error" />
									</div>

									<div class="col-12">
										<label class="form-label">Email</label>
										<form:input path="email" placeholder="name@example.com"
											class="form-control" />
										<form:errors path="email" cssClass="error" />
									</div>

									<div class="col-md-5">
										<label class="form-label">Uprawnienia</label>
										<form:select path="role" items="${roles}" class="form-select" />
									</div>

								</div>

								<div class="col-12">
									<button type="submit" class="btn btn-outline-primary">Zatwierdź</button>
								</div>
								
							</form:form>
						</div>
					</aside>
					<aside class="col-sm-4"></aside>
				</div>
			</main>

		</div>
	</div>
</body>
</html>