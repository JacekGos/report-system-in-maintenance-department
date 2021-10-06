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
					<h1 class="h2">Opcje użytkownika</h1>
					<div class="btn-toolbar mb-2 mb-md-0">
						<div class="btn-group me-2">
							<button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
							<button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
						</div>
						<button type="button"
							class="btn btn-sm btn-outline-secondary dropdown-toggle">
							<span data-feather="calendar"></span> This week
						</button>
					</div>
				</div>


				<div class="row">
					<aside class="col-sm-4">

<%-- 						<c:url var="changePasswordLink" value="/user/changeUserPassword"> --%>
<%-- 							<c:param name="userId" value="${userId}" /> --%>
<%-- 						</c:url> --%>
<%-- 						<a class="btn btn-outline-primary" href="${changePasswordLink}" --%>
<!-- 							role="button">Zmiana hasła</a> -->

					</aside>
					<aside class="col-sm-7"></aside>
					<aside class="col-sm-4"></aside>
				</div>
			</main>
		</div>
	</div>

</body>
</html>