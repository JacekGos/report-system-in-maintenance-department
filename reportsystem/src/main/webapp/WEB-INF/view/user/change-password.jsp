<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">

<head>

<title>Register New User Form</title>

<%@ include file="/WEB-INF/view/sources.jsp"%>

</head>

<body>

	<%@ include file="/WEB-INF/view/navigation-bar.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<%@ include file="/WEB-INF/view/side-bar-menu.jsp"%>

			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<h1 class="h2">Zmiana hasła</h1>
				</div>

				<div class="row">
					<aside class="col-sm-4"></aside>
					<aside class="col-sm-7">

						<form:form class="row g-3"
							action="${pageContext.request.contextPath}/user/processChangePassword"
							modelAttribute="changePasswordDto" accept-charset="UTF-8">

							<div class="col-md-4">
								
<%-- 								<h3>Użytkownik: <security:authentication property="principal.username" /></h3><br> --%>
								
								<form:hidden path="id" />

								<label class="form-label">Nowe hasło</label>
								<form:input path="password" placeholder="*****"
									class="form-control" type="password" />

								<form:errors path="password" cssClass="error" /><br>

								<label class="form-label">Powtórz hasło</label>
								<form:input path="matchingPassword" placeholder="*****"
									class="form-control" type="password" />

								<form:errors path="matchingPassword" cssClass="error" />

							</div>

							<div class="col-12">
								<button type="submit" class="btn btn-outline-primary">Zatwierdź</button>
							</div>

						</form:form>

						<c:if test="${changePasswordSuccess != null}">
							<div class="alert alert-success col-xs-offset-1 col-xs-10 col-4">
								${changePasswordSuccess}</div>
						</c:if>
						<c:if test="${changePasswordError != null}">
							<div class="alert alert-danger col-xs-offset-1 col-xs-10 col-4">
								${changePasswordError}</div>
						</c:if>

					</aside>

					<aside class="col-sm-4"></aside>
				</div>
			</main>
		</div>
	</div>

</body>
</html>