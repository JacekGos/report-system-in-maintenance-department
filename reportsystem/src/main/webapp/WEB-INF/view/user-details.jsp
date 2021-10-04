<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>

<head>
<title>Home Page</title>

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
					<h1 class="h2">Dane użytkownika</h1>
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
					<aside class="col-sm-4"></aside>
					<aside class="col-sm-3">
						<div class="table-responsive">
							<table
								class="table table-striped table-sm table-bordered table-hover">
								<thead class="table-dark">
									<tr>
										<!-- <th scope="col" class="col-sm-6">Imię</th>
										<th scope="col" class="col-sm-3">Nazwisko</th>
										<th scope="col" class="col-sm-3">Email</th> -->

										<th scope="col">Imię</th>
										<th scope="col">Nazwisko</th>
										<th scope="col">Email</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${user.firstName}</td>
										<td>${user.lastName}</td>
										<td>${user.email}</td>
									</tr>

								</tbody>
							</table>

							<c:url var="detailsLink" value="/user/showUpdateUserForm">
								<c:param name="id" value="${user.id}" />
							</c:url>
							<c:url var="resetPasswordLink" value="/user/resetPassword">
								<c:param name="id" value="${user.id}" />
							</c:url>
							<c:url var="blockUserLink" value="/user/blockUser">
								<c:param name="id" value="${user.id}" />
							</c:url>

							<a class="btn btn-outline-primary" href="${detailsLink}"
								role="button"> Edycja</a> <a class="btn btn-outline-primary"
								href="${resetPasswordLink}" role="button"> Reset hasła</a> <a
								class="btn btn-outline-primary" href="${blockUserLink}"
								role="button"> Blokuj</a>

						</div>
					</aside>
					<aside class="col-sm-4"></aside>
				</div>

			</main>

		</div>
	</div>

</body>
</html>