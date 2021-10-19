<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">

<head>

<title>Register New User Form</title>

<%@ include file="/WEB-INF/view/navigation/sources.jsp"%>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/form-validation.css" />

</head>

<body>

	<%@ include file="/WEB-INF/view/navigation/navigation-bar.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<%@ include file="/WEB-INF/view/navigation/side-bar-menu.jsp"%>

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
								action="${pageContext.request.contextPath}/user/processSaveUser"
								modelAttribute="userDto" accept-charset="UTF-8">
								
								<form:hidden path="firstName" />
								<form:hidden path="lastName" />
								<form:hidden path="password" />
								<form:hidden path="userName" />
								<form:hidden path="email" />
								<form:hidden path="role" />
								
								<div class="row g-3">
									<div class="col-sm-6">
										<label class="form-label">Imię</label> <input type="text"
											placeholder="${userDto.firstName}" class="form-control"
											disabled readonly>
									</div>

									<div class="col-sm-6">
										<label class="form-label">Nazwisko</label> <input type="text"
											placeholder="${userDto.lastName}" class="form-control"
											disabled readonly>
									</div>

									<div class="col-sm-6">
										<label class="form-label">Nazwa użytkownika</label> <input
											type="text" placeholder="${userDto.userName}"
											class="form-control" disabled readonly>
									</div>

									<div class="col-sm-6">
										<label class="form-label">Uprawnienia</label> <input
											type="text" placeholder="${userDto.role}"
											class="form-control" disabled readonly>
									</div>

									<div class="col-6">
										<label class="form-label">Email</label> <input type="text"
											placeholder="${userDto.email}" class="form-control" disabled
											readonly>
									</div>

								</div>

								<div class="col-12">
									<button type="submit" class="btn btn-outline-primary">Zatwierdź</button>
								</div>
								
								<c:if test="${registrationError != null}">
									<div class="alert alert-danger col-xs-offset-1 col-xs-10">
											${registrationError}</div>
								</c:if>
								
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