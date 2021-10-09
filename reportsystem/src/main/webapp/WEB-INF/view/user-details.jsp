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

<!-- <script>
	$('#exampleModal').on('shown.bs.modal', function() {
		$('#myInput').trigger('focus')
	})

	$('#exampleModal').modal({
		backdrop : 'static',
		keyboard : false
	})
</script> -->

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
					<aside class="col-sm-3"></aside>
					<aside class="col-sm-6">
						<div class="table-responsive">
							<table
								class="table table-striped table-sm table-bordered table-hover">
								<thead class="table-dark">
									<tr>
										<th scope="col">Imię</th>
										<th scope="col">Nazwisko</th>
										<th scope="col">Nazwa użytkownika</th>
										<th scope="col">Email</th>
										<th scope="col">Uprawnienia</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${user.firstName}</td>
										<td>${user.lastName}</td>
										<td>${user.userName}</td>
										<td>${user.email}</td>
										<td>${user.getRoleName()}</td>
									</tr>

								</tbody>
							</table>

							<c:url var="detailsLink" value="/user/showUpdateUserForm">
								<c:param name="id" value="${user.id}" />
							</c:url>

							<c:url var="resetPasswordLink" value="/user/resetUserPassword">
								<c:param name="id" value="${user.id}" />
							</c:url>

							<c:url var="deactivateUserLink"
								value="/user/deactivateUserAccount">
								<c:param name="id" value="${user.id}" />
							</c:url>

							<c:url var="activateUserLink" value="/user/activateUserAccount">
								<c:param name="id" value="${user.id}" />
							</c:url>


							<button type="button" class="btn btn-outline-primary"
								data-bs-toggle="modal" data-bs-target="#submitModal">
								Reset hasła</button>

							<div class="modal fade" id="submitModal" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">Jesteś pewny?</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Close</button>
											<a class="btn btn-outline-primary"
												href="${resetPasswordLink}" role="button">Zatwierdź</a>
										</div>
									</div>
								</div>
							</div>

							<c:if test="${currentUserName != user.userName}">
								<c:if test="${user.enabled == true}">
									<button type="button" class="btn btn-outline-primary"
										data-bs-toggle="modal" data-bs-target="#submitModal2">
										Dezaktywuj</button>

									<div class="modal fade" id="submitModal2" tabindex="-1"
										aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="btn-close"
														data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<div class="modal-body">Jesteś pewny?</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">Close</button>
													<a class="btn btn-outline-primary"
														href="${deactivateUserLink}" role="button">Zatwierdź</a>
												</div>
											</div>
										</div>
									</div>
								</c:if>

								<c:if test="${user.enabled != true}">
									<button type="button" class="btn btn-outline-primary"
										data-bs-toggle="modal" data-bs-target="#submitModal2">
										Aktywuj</button>

									<div class="modal fade" id="submitModal2" tabindex="-1"
										aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="btn-close"
														data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<div class="modal-body">Jesteś pewny?</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">Close</button>
													<a class="btn btn-outline-primary"
														href="${activateUserLink}" role="button">Zatwierdź</a>
												</div>
											</div>
										</div>
									</div>
								</c:if>

								<button type="button" class="btn btn-outline-primary"
									data-bs-toggle="modal" data-bs-target="#exampleModal">
									Zmiana uprawnień</button>

								<div class="modal fade" id="exampleModal" tabindex="-1"
									aria-labelledby="exampleModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel">Zmiana
													uprawnień</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>

											<div class="modal-body">

												<form:form class="row g-3"
													action="${pageContext.request.contextPath}/user/setUserRole"
													modelAttribute="userDto" accept-charset="UTF-8">

													<form:hidden path="id" />
													<form:hidden path="firstName" />
													<form:hidden path="lastName" />
													<form:hidden path="password" />
													<form:hidden path="userName" />
													<form:hidden path="email" />
													<form:hidden path="enabled" />
													<form:hidden path="credentialsNonExpired" />
													<form:hidden path="nonExpired" />
													<form:hidden path="nonLocked" />

													<div class="col-md-5">
														<label class="form-label">Uprawnienia</label> <input
															type="text" placeholder="${userDto.role}"
															class="form-control" disabled readonly>

														<form:select path="role" items="${roles}"
															class="form-select" />
														<br>
														<button type="submit" class="btn btn-outline-primary">
															Zapisz zmiany</button>

													</div>

												</form:form>

											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Zamknij</button>

											</div>
										</div>
									</div>
								</div>
							</c:if>

						</div>
					</aside>
					<aside class="col-sm-4"></aside>
				</div>

			</main>

		</div>
	</div>

</body>
</html>